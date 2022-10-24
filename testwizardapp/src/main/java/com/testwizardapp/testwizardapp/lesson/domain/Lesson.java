package com.testwizardapp.testwizardapp.lesson.domain;

import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


@Data
public class Lesson implements Comparable<Lesson> {
    private String id;
    private String name;

    private boolean root = false;
    private Set<Lesson> parents = new TreeSet<>();
    private Set<Lesson> children = new TreeSet<>();

    public Lesson(List<String> lessonTree) {
        this.id = lessonTree.get(0);
        this.name = lessonTree.get(0);
        this.root = true;
        this.parents = Collections.EMPTY_SET;
        var subList = lessonTree.subList(1, lessonTree.size());
        if (!subList.isEmpty()) {
            this.children.add(new Lesson(subList, this));
        }
    }

    private Lesson(List<String> lessonTree, Lesson parent) {
        this.id = lessonTree.get(0);
        this.name = lessonTree.get(0);
        this.parents.add(parent);
        var subList = lessonTree.subList(1, lessonTree.size());
        if (!subList.isEmpty()) {
            this.children.add(new Lesson(subList, this));
        }
    }

    public Lesson addTree(List<String> lessonTree) {
        //TODO addition impl
        return this;
    }

    @Override
    public int compareTo(Lesson lesson) {
        return this.name.compareTo(lesson.name);
    }
}

