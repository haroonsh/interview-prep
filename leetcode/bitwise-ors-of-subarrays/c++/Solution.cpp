class Solution {
public:
    int subarrayBitwiseORs(vector<int>& A) {
        std::set<int> ans;
        std::set<int> curr;
        curr.insert(0);
        for (std::vector<int>::iterator it = A.begin() ; it != A.end(); ++it) {
            std::set<int> newCurr;
            for (std::set<int>::iterator setIt = curr.begin(); setIt != curr.end(); ++setIt) {
                newCurr.insert((*it) | (*setIt));
            }
            newCurr.insert((*it));
            curr = newCurr;
            for (std::set<int>::iterator setIt = newCurr.begin(); setIt != newCurr.end(); ++setIt) {
                ans.insert((*setIt));
            }
        }
        
        return ans.size();
    }
};
