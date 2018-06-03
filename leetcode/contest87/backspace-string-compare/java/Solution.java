class Solution {
    public boolean backspaceCompare(String S, String T) {
        StringBuilder newS = new StringBuilder();
        StringBuilder newT = new StringBuilder();
        
        builderHelper(newS, S);
        builderHelper(newT, T);
        
        return newS.toString().equals(newT.toString());
    }
    
    public void builderHelper(StringBuilder sb, String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '#') {
                sb.append(str.charAt(i));
            } else if (str.charAt(i) == '#') {
                if (sb.length() > 0) {
                    sb.delete(sb.length() - 1, sb.length());   
                }
            }
        }
    }
}
