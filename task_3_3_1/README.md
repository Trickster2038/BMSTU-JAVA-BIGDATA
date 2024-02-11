Output example

```
C:\Users\sergey.astakhov\.jdks\openjdk-21\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2023.2.2\lib\idea_rt.jar=57810:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2023.2.2\bin" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath C:\Users\sergey.astakhov\Desktop\BMSTU-JAVA-BIGDATA\task_3_3_1\target\classes org.example.Main
=== Text file work example ===


=== Files ===


Filepath: C:\Users\sergey.astakhov\Desktop\BMSTU-JAVA-BIGDATA\task_3_3_1\a.txt (rights r-w-x: true-true-true) 
Hash: fb58e8ba 
Text: aaaaaa

Filepath: C:\Users\sergey.astakhov\Desktop\BMSTU-JAVA-BIGDATA\task_3_3_1\a_copy.txt (rights r-w-x: true-true-true) 
Hash: fb58e8ba 
Text: aaaaaa

Filepath: C:\Users\sergey.astakhov\Desktop\BMSTU-JAVA-BIGDATA\task_3_3_1\a1.txt (rights r-w-x: true-true-true) 
Hash: e8e5de97 
Text: a1a1a1a1a1a

Filepath: C:\Users\sergey.astakhov\Desktop\BMSTU-JAVA-BIGDATA\task_3_3_1\b.txt (rights r-w-x: true-true-true) 
Hash: 974696af 
Text: bbbbbbbbbbbbbbbbb
b1b1b1

=== Rename a1 -> a2 ===

false
Filepath: C:\Users\sergey.astakhov\Desktop\BMSTU-JAVA-BIGDATA\task_3_3_1\a1.txt (rights r-w-x: true-true-true) 
Hash: e8e5de97 
Text: a1a1a1a1a1a

=== Equals ===

a.equals(a): true
a.equals(a_copy): false
a.equals(b): false


Enter a string to delete files and finish: 

exit
true
true
true
true

```
