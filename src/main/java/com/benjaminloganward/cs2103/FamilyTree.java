package com.benjaminloganward.cs2103;

import java.util.ArrayList;
import java.util.Collection;

public class FamilyTree {
    class PersonNode {
        String _name;
        PersonNode _parent;
        Collection<PersonNode> _children;
    }

    public Collection<PersonNode> getAllKDescendants(PersonNode personNode, int k) {
        ArrayList<PersonNode> descendants = new ArrayList<>();
        if (k == 0)
            descendants.add(personNode);
        else
            for (PersonNode node : personNode._children)
                descendants.addAll(getAllKDescendants(node, k - 1));
        return descendants;
    }

    public Collection<PersonNode> getAllKLaterals(PersonNode personNode, int k) {
        ArrayList<PersonNode> laterals = new ArrayList<>();
        PersonNode kAncestor = getKAncestor(personNode, k), kM1Ancestor = getKAncestor(personNode, k - 1);
        if (kAncestor == null || kM1Ancestor == null) return laterals;
        for (PersonNode node : getAllKDescendants(kAncestor, k)) {
            if (!kM1Ancestor.equals(getKAncestor(node, k - 1)))
                laterals.add(node);
        }
        return laterals;
    }

    private PersonNode getKAncestor(PersonNode node, int k) {
        if(k == 0) return node;
        return node._parent != null ? getKAncestor(node._parent, k - 1) : null;
    }

    public static void main(String[] args) {

    }
}
