
/**
 * Write a description of FindAbc here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FindAbc {
    /**
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1) {
                break;
            }
            int indx1 = index + 1;
            int indx4 = index + 4;
            System.out.println("index1 " + indx1 + " index4 " + indx4);
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+4);
        }
    }
    **/
    
    public void findAbc(String input){
       int index = input.indexOf("abc");
       while (true){
           int indx = index;
           System.out.println("index: "  + index);
           if (index == -1 || index >= input.length() - 3){
               break;
           }
           String found = input.substring(index+1, index+4);
           System.out.println(found);
           index = input.indexOf("abc",index+3);
       }
   }
    public void test() {
        //findAbc("abcdabc");
         //      0123456
        //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
        //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
        findAbc("abcabcabcabca");
    }
}
