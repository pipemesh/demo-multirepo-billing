package dev.pipemesh.demo.billing;

import dev.pipemesh.demo.orders.client.Order;
import dev.pipemesh.demo.orders.client.OrdersClient;

/**
 * The billing service. It calls orders through the client library, which it
 * receives as a jar the pipeline built in another repository.
 */
public final class Billing {

    static long invoiceCents(OrdersClient orders, String customer) {
        return orders.ordersOf(customer).stream().mapToLong(Order::totalCents).sum();
    }

    public static void main(String[] args) {
        var orders = new OrdersClient("http://orders.internal");
        long invoice = invoiceCents(orders, "acme");
        if (args.length > 0 && args[0].equals("--self-test")) {
            if (invoice != 2100) throw new AssertionError("unexpected invoice: " + invoice);
            System.out.println("billing: self-test passed against orders client " + OrdersClient.VERSION);
            return;
        }
        System.out.println("billing: invoice for acme, " + invoice + " cents");
    }
}
