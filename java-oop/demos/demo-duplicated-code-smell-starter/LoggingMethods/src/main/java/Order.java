public class Order {
    private Item[] items;

    private int itemCount;

    private int orderId;

    public Order() {
        items = new Item[5];
        itemCount = 0;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void addItem(Item i) {
        if(itemCount < items.length) {
            items[itemCount] = i;
            itemCount++;
        }
    }

    public Item[] getItems() {
        return items;
    }

}
