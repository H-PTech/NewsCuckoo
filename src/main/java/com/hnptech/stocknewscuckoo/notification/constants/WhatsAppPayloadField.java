package com.hnptech.stocknewscuckoo.notification.constants;

public enum WhatsAppPayloadField {

    MESSAGING_PRODUCT("messaging_product", "whatsapp"),
    RECIPIENT_TYPE("recipient_type", "individual"),
    PREVIEW_URL("preview_url", "true"),
    TEXT_TYPE("type", "text");

    private final String key;
    private final String value;

    WhatsAppPayloadField(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }


    /**
     * WhatsApp 메시지 전송 API
     * @example
     * curl -X POST 'https://graph.facebook.com/v21.0/106540352242922/messages' \
     *   -H 'Content-Type: application/json' \
     *   -H 'Authorization: Bearer EAAJB...' \
     *   -d '{
     *     "messaging_product": "whatsapp",
     *     "recipient_type": "individual",
     *     "to": "+16505551234",
     *     "type": "text",
     *     "text": {
     *       "preview_url": true,
     *       "body": "As requested, here\'s the link to our latest product: https://www.meta.com/quest/quest-3/"
     *     }
     *   }'

     */
    public static String buildPayload(String recipientPhoneNumber, String messageBody) {
        return String.format(
                """
                        {
                            "%s": "%s",
                            "%s": "%s",
                            "to": "%s",
                            "%s": "%s",
                            "text": {
                                "%s": %s,
                                "body": "%s"
                            }
                        }
                        """,
                MESSAGING_PRODUCT.getKey(), MESSAGING_PRODUCT.getValue(),
                RECIPIENT_TYPE.getKey(), RECIPIENT_TYPE.getValue(),
                recipientPhoneNumber,
                TEXT_TYPE.getKey(), TEXT_TYPE.getValue(),
                PREVIEW_URL.getKey(), PREVIEW_URL.getValue(),
                messageBody
        );
    }
}