import java.util.Arrays;

/**
 * Define a function RemoveDuplicate(nlist) to remove duplicate elements from list.
 *  Here are some examples:
 *      null -> []
 *      [] -> []
 *      [1,2] -> [1,2]
 *      [1,1,2,2,3,3] -> [1,2,3]
 **/
public class RemoveDuplicated {

    public static int[] removeDuplicate(int[] list) {
        if(list == null || list.length == 0) {
            int[] emptyArr = {};
            return emptyArr;
        }

        return Arrays.stream(list).distinct().toArray();
    }

}
