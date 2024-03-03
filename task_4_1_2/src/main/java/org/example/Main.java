package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("=== Mp3Disk class === ");

        Mp3Disk mp3 = new Mp3Disk("Deep Purple");
        mp3.getRoot().add("dir", ".trashbox");
        mp3.getRoot().getChildDir(".trashbox").add("file", ".trojan.exe");
        mp3.getRoot().add("dir", "top songs");
        mp3.getRoot().getChildDir("top songs").add("file", "smoke on the water");
        mp3.getRoot().getChildDir("top songs").add("file", "and fire in the sky");
        mp3.getRoot().add("dir", ".autorun");
        mp3.getRoot().getChildDir(".autorun").add("dir", "utils");
        mp3.getRoot().getChildDir(".autorun").getChildDir("utils").add("file", "driver.exe");

        System.out.println(mp3);
    }
}

class Mp3Disk {

    private FileSysObject root = new FileSysObject("dir", "root");

    private String name;

    public Mp3Disk(String name) {
        this.name = name;
    }
    public FileSysObject getRoot() {
        return root;
    }

    public String toString() {
        return String.format("<Disk> %s\n%s", name, root.toStringLeveled(1));
    }

    class FileSysObject{
        private ArrayList<FileSysObject> innerObjects = new ArrayList<>();
        private String type;
        private String name;

        public FileSysObject(String type, String name) {
            this.type = type;
            this.name = name;
        }

        public void add(String type, String name) {
            innerObjects.add(new FileSysObject(type, name));
        }

        public String toString() {
            String s = "";
            if(type.equals("file")){
                s = String.format("<%s> %s", type, name);
            }
            else {
                s = String.format("<%s> %s", type, name);
                for(FileSysObject x: innerObjects){
                    s = String.format("%s\n\t%s", s, x.toString());
                }
            }
            return s;
        }

        public String toStringLeveled(int lvl) {
            String s = "";
            if(type.equals("file")){
                s = String.format("<%s> %s", type, name);
            }
            else {
                s = String.format("<%s> %s", type, name);
                for(FileSysObject x: innerObjects){
                    String tabs = "";
                    for(int i = 0; i < lvl; i++) {
                        tabs += "\t";
                    }
                    s = String.format("%s\n%s%s", s, tabs ,x.toStringLeveled(lvl + 1));
                }
            }
            return s;
        }

        public FileSysObject getChildDir(String name) throws Exception {
            for(FileSysObject x : innerObjects){
                if (x.name.equals(name) && x.type.equals("dir")) {
                    return x;
                }
            }
            throw new Exception("no such dir");
        }
    }
}