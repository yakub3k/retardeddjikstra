# Exampel data

[Graph] n: 10 edgeCount: 36
[Edge m] selected: 0 -> 9 weight: 3
[Edge m] selected: 9 -> 3 weight: 4
[Edge m] selected: 3 -> 7 weight: 6
[Edge m] selected: 7 -> 8 weight: 6
[Edge m] selected: 8 -> 4 weight: 8
[Edge m] selected: 4 -> 5 weight: 1
[Edge m] selected: 5 -> 2 weight: 5
[Edge m] selected: 2 -> 6 weight: 1
[Edge m] selected: 6 -> 1 weight: 9
[Edge m] selected: 1 -> 0 weight: 9
[Edge o] selected: 0 -> 5 weight: 4
[Edge o] selected: 1 -> 9 weight: 5
[Edge o] selected: 3 -> 6 weight: 9
[Edge o] selected: 4 -> 7 weight: 8
[Edge o] selected: 9 -> 4 weight: 1
[Edge o] selected: 5 -> 9 weight: 4
[Edge o] selected: 1 -> 2 weight: 1
[Edge o] selected: 2 -> 9 weight: 2
[Edge o] selected: 9 -> 8 weight: 6
[Edge o] selected: 7 -> 6 weight: 3
[Edge o] selected: 3 -> 8 weight: 3
[Edge o] selected: 7 -> 0 weight: 7
[Edge o] selected: 3 -> 4 weight: 4
[Edge o] selected: 4 -> 1 weight: 3
[Edge o] selected: 8 -> 2 weight: 2
[Edge o] selected: 9 -> 7 weight: 3
[Edge o] selected: 4 -> 6 weight: 8
[Edge o] selected: 5 -> 3 weight: 6
[Edge o] selected: 5 -> 8 weight: 1
[Edge o] selected: 7 -> 1 weight: 1
[Edge o] selected: 7 -> 5 weight: 3
[Edge o] selected: 5 -> 6 weight: 2
[Edge o] selected: 0 -> 2 weight: 7
[Edge o] selected: 5 -> 1 weight: 8
[Edge o] selected: 6 -> 8 weight: 4
[Edge o] selected: 3 -> 2 weight: 9
[Edge o] selected: 9 -> 6 weight: 5
Neighbors:

[0] 9 5 2 7 1 
[1] 2 7 4 9 5 0 6 
[2] 1 6 8 9 5 0 3 
[3] 8 4 9 5 7 2 6 
[4] 5 9 1 3 6 7 8 
[5] 4 8 6 7 0 9 2 3 1 
[6] 2 5 7 8 9 4 1 3 
[7] 1 5 6 9 3 8 0 4 
[8] 5 2 3 6 7 9 4 
[9] 4 2 0 7 3 5 1 6 8 


int[][] graph = {
 { 0, 9, 7, 0, 0, 4, 0, 7, 0, 3, },
 { 9, 0, 1, 0, 3, 8, 9, 1, 0, 5, },
 { 7, 1, 0, 9, 0, 5, 1, 0, 2, 2, },
 { 0, 0, 9, 0, 4, 6, 9, 6, 3, 4, },
 { 0, 3, 0, 4, 0, 1, 8, 8, 8, 1, },
 { 4, 8, 5, 6, 1, 0, 2, 3, 1, 4, },
 { 0, 9, 1, 9, 8, 2, 0, 3, 4, 5, },
 { 7, 1, 0, 6, 8, 3, 3, 0, 6, 3, },
 { 0, 0, 2, 3, 8, 1, 4, 6, 0, 6, },
 { 3, 5, 2, 4, 1, 4, 5, 3, 6, 0, },
}
[Path] Start from: 0 to: 0
[Path] poll 0 (0)
[Path] poll 2 (7)
[Path] poll 1 (8)
[Path] poll 7 (7)
[Path] poll 9 (3)
[Path] poll 5 (4)
[Path] poll 4 (4)
[Path] poll 8 (5)
[Path] poll 6 (6)
[Path] poll 3 (7)
[Path] Finish - 1 millis
[Path] Finish - relax: 10 set: 19
[Points] 0 = 0
Picked up JAVA_TOOL_OPTIONS:  

Process finished with exit code 0
