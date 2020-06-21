package fb;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/*
On a single threaded CPU, we execute some functions.  Each function has a unique id between 0 and N-1.
We store logs in timestamp order that describe when a function is entered or exited.
Each log is a string with this format: "{function_id}:{"start" | "end"}:{timestamp}".  For example, "0:start:3" means the function with id 0 started at the beginning of timestamp 3.  "1:end:2" means the function with id 1 ended at the end of timestamp 2.
A function's exclusive time is the number of units of time spent in this function.  Note that this does not include any recursive calls to child functions.
The CPU is single threaded which means that only one function is being executed at a given time unit.
Return the exclusive time of each function, sorted by their function id.

Example 1:

Input:
n = 2
logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
Output: [3, 4]
Explanation:
Function 0 starts at the beginning of time 0, then it executes 2 units of time and reaches the end of time 1.
Now function 1 starts at the beginning of time 2, executes 4 units of time and ends at time 5.
Function 0 is running again at the beginning of time 6, and also ends at the end of time 6, thus executing for 1 unit of time.
So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.
 */
/*
use a stack to store ids. keep track of a globalPrev time (init to 0)
use an array to store result
for each log:
    if it's an end: add cur id result with cur.time - globalPrev + 1. pop top. update globalPrev with cur time + 1
    if it's a start: add prev id result (get prev id from stack top) with cur.time - globalPrev. update globalPrev with cur time. push to stack
*/
public class ExclusiveTimeOfFunctions {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        int globalPrev = 0;
        Deque<Integer> taskStack = new ArrayDeque<>();
        for (String log : logs) {
            String[] arr = log.split(":");
            int id = Integer.parseInt(arr[0]);
            int time = Integer.parseInt(arr[2]);
            if (taskStack.isEmpty()) {
                taskStack.offerFirst(id);
                globalPrev = time;
                continue;
            }
            if (arr[1].equals("end")) {
                result[id] += time - globalPrev + 1;
                taskStack.pollFirst();
                globalPrev = time + 1;
            } else {
                int prevId = taskStack.peekFirst();
                result[prevId] += time - globalPrev;
                taskStack.offerFirst(id);
                globalPrev = time;
            }
        }
        return result;
    }
}
