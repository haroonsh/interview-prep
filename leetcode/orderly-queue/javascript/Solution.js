/**
 * @param {string} S
 * @param {number} K
 * @return {string}
 */
var orderlyQueue = function(S, K) {
    var best = S;
    if (K == 1) {
        // consider rotations
        for (var i = 0; i < S.length; i++) {
            var temp = S.substring(i) + S.substring(0, i);
            if (temp < best) {
                best = temp;
            }
        }
    } else {
        // sort S
        S = S.split("").sort();
        best = S.join("");
        return best;
    }
    return best;
};
