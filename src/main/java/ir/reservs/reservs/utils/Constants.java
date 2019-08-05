package ir.reservs.reservs.utils;

/**
 * Created by mhrezai on 03/10/2018.
 */

public class Constants {
    public static final String BASE_URL = "http://reserve-sport.ir/api/mobile/";
    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";
    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;
    private static final String PAYMENT_GATEWAY_URL= "https://sandbox.%szarinpal.com/pg/StartPay/%s/ZarinGate";
    private static final String PAYMENT_REQUEST_URL="https://sandbox.%szarinpal.com/pg/rest/WebGate/PaymentRequest.json";
    private static final String VERIFICATION_PAYMENT_URL="https://sandbox.%szarinpal.com/pg/rest/WebGate/PaymentVerification.json";

    public static String getStartPaymentGatewayURL(String authority) {
        return String.format(PAYMENT_GATEWAY_URL, "", authority);
    }

    public static String getPaymentRequestURL() {
        return String.format(PAYMENT_REQUEST_URL, "");
    }

    public static String getVerificationPaymentURL() {
        return String.format(VERIFICATION_PAYMENT_URL, "");
    }
}
