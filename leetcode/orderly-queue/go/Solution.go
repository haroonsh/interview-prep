func orderlyQueue(S string, K int) string {
    best := S
    if K == 1 {
        // compute rotations only
        for i := 0; i < len(S); i++ {
            var buffer bytes.Buffer
            buffer.WriteString(S[i:])
            buffer.WriteString(S[0:i])
            if strings.Compare(best, buffer.String()) > 0 {
                best = buffer.String()
            }
        }
    } else {
        // order charaters lexiographically
        keys := make([]string, len(S))
        for i := 0; i < len(S); i++ {
            key := string(S[i])
            keys = append(keys, key)
        }
        
        // sort keys
        sort.Strings(keys);
        var buffer bytes.Buffer
        for _, val := range keys {
            buffer.WriteString(val)
        }
        best = buffer.String()
    }
    return best
}
