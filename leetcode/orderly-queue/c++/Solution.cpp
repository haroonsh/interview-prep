class Solution {
public:
    string orderlyQueue(string S, int K) {
        string best = S;
        if (K == 1) {
            // consider rotations only
            for (int i = 0; i < S.length(); i++) {
                string temp = S.substr(i) + S.substr(0, i);
                if (temp < best) {
                    best = temp;
                }
            }
        } else {
            // sort S
            sort(best.begin(), best.end());
        }
        return best;
    }
};
