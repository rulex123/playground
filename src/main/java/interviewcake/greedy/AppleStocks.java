package interviewcake.greedy;

/**
 * Writing programming interview questions hasn't made me rich yet ... so I might give up and start
 * trading Apple stocks all day instead.
 *
 * First, I wanna know how much money I could have made yesterday if I'd been trading Apple stocks
 * all day.
 *
 * So I grabbed Apple's stock prices from yesterday and put them in an array called stockPrices,
 * where:
 *
 * The indices are the time (in minutes) past trade opening time, which was 9:30am local time.
 * The values are the price (in US dollars) of one share of Apple stock at that time.
 * So if the stock cost $500 at 10:30am, that means stockPrices[60] = 500.
 *
 * Write an efficient method that takes stockPrices and returns the best profit I could have made
 * from one purchase and one sale of one share of Apple stock yesterday.
 *
 * For example:
 *
 * int[] stockPrices = new int[] {10, 7, 5, 8, 11, 9};
 *
 * getMaxProfit(stockPrices);
 * // returns 6 (buying for $5 and selling for $11)
 *
 * No "shorting" - you need to buy before you can sell. Also, you can't buy and sell in the same time
 * step - at least 1 minute has to pass.
 */
public class AppleStocks {

  public static void main(String[] args) {
    int[] stockPrice = new int[]{ 10, 7, 5, 2, 8, 11, 9, 15 };
    System.out.println(appleStocks(stockPrice));

    stockPrice = new int[]{ 10, 8, 6, 4, 2 };
    System.out.println(appleStocks(stockPrice));
  }

  static int appleStocks(int[] stockPrices) {
    if (stockPrices == null || stockPrices.length < 2) // there must at least be 2 stock prices
    // for us to be able to buy and sell
    {
      throw new IllegalArgumentException("must have at least 2 prices!");
    }

    int minBuyPrice = stockPrices[0];
    int maxProfit = Integer.MIN_VALUE;
    for (int i = 1; i < stockPrices.length; i++) {
      // what is the profit if we sell at current price?
      int currentPrice = stockPrices[i];
      int profit = currentPrice - minBuyPrice;

      if (profit > maxProfit) {
        maxProfit = profit; // keep track of max profit seen so far
      }

      if (currentPrice < minBuyPrice) {
        minBuyPrice = currentPrice; // keep track of min price seen so far
      }
    }
    return maxProfit;
  }
}
