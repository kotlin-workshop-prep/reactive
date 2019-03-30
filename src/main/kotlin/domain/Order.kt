package domain

data class LineItem(val product: Product, val quantity: Int)

data class Order(val id: Int, val lineItems: List<LineItem>, val customer: Customer)