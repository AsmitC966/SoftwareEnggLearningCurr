/*
    I find the lowest price and the highest to sell

    1st to find the lowest we iterate and replace lowest like selection sort

    keep geenrating the max Profit obtainable

    return that
*/

class Q121
{
    public int maxProfit(int[] prices)
    {
        int minPrice = prices[0];
        int maxProfit=0;
        
        for (int price:prices)
        {
            if (price < minPrice)
                minPrice=price;
            else
                maxProfit=Math.max(maxProfit, price-minPrice);
        }
        return maxProfit;
    }
}