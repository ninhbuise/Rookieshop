package com.enashtech.rookieserver.entity;

public enum Status {
    PROCESSING, // When the state of new orders is set to ‘Processing’, the Automatically Invoice All Items option becomes available in the configuration.
    PENDING, //	This status means no invoice and shipments have been submitted.
    SHIPPING, // This status when the order on the way to customer
    COMPLETE, // This status means that the order is created, paid, and shipped to customer.
    CANCELED, // This status is assigned manually in the Admin or, for some payment gateways, when the customer does not pay within the specified time.
    ACTIVE, // This status mean user can use systeam
    BANNED // this staus mean user have been ban from system
}
