class StockSpanner {

    Stack<Integer> prices;
    Stack<Integer> weights;
    public StockSpanner() {
        prices = new Stack<Integer>();
        weights = new Stack<Integer>();
    }
    
    public int next(int price) {
        int total = 1;
        while (! this.prices.isEmpty() && this.prices.peek() <= price) {
            Integer p = this.prices.pop();
            Integer w = this.weights.pop();
            total += w;
        }
        prices.push(price);
        weights.push(total);
        return total;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
