func isMonotonic(A []int) bool {
    if len(A) == 0 {
        return true
    }
    
    increasing := true
    decreasing := true
    
    prev := A[0]
    for i := 1; i < len(A); i++ {
        increasing = increasing && (prev <= A[i])
        decreasing = decreasing && (prev >= A[i])
        prev = A[i]
    }
    
    return increasing || decreasing
}