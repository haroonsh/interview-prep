class Solution {
public:
    bool isMonotonic(vector<int>& A) {
        bool increasing = true;
        bool decreasing = true;
        
        if (A.size() == 0) {
            return true;
        }
        int prev = A.at(0);
        for (std::vector<int>::iterator it = A.begin(); it != A.end(); it++) {
            increasing = increasing && (prev <= (*it));
            decreasing = decreasing && (prev >= (*it));
            prev = (*it);
        }
        
        return increasing || decreasing;
    }
};
