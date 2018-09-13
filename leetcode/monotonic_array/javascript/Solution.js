/**
 * @param {number[]} A
 * @return {boolean}
 */
"use strict";
var isMonotonic = function(A) {
    if (A.length == 0) {
        return true;
    }
    
    let increasing = true;
    let decreasing = true;
    
    let prev = A[0];
    for (var i = 1; i < A.length; i++) {
        increasing = increasing && (prev <= A[i]);
        decreasing = decreasing && (prev >= A[i]);
        prev = A[i];
    }
    
    return increasing || decreasing;
};
