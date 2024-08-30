package one.modality.base.client.icons;

import javafx.scene.shape.SVGPath;

/**
 * @author Bruno Salmon
 */
public final class SvgIcons {

    static final String TRASH_SVG_PATH = "M8 3.58537H12C12 3.042 11.7893 2.52088 11.4142 2.13666C11.0391 1.75244 10.5304 1.53659 10 1.53659C9.46957 1.53659 8.96086 1.75244 8.58579 2.13666C8.21071 2.52088 8 3.042 8 3.58537ZM6.5 3.58537C6.5 3.11453 6.59053 2.6483 6.76642 2.21331C6.94231 1.77831 7.20012 1.38306 7.52513 1.05013C7.85013 0.717197 8.23597 0.453101 8.66061 0.27292C9.08525 0.0927383 9.54037 0 10 0C10.4596 0 10.9148 0.0927383 11.3394 0.27292C11.764 0.453101 12.1499 0.717197 12.4749 1.05013C12.7999 1.38306 13.0577 1.77831 13.2336 2.21331C13.4095 2.6483 13.5 3.11453 13.5 3.58537H19.25C19.4489 3.58537 19.6397 3.66631 19.7803 3.81039C19.921 3.95448 20 4.14989 20 4.35366C20 4.55742 19.921 4.75284 19.7803 4.89692C19.6397 5.04101 19.4489 5.12195 19.25 5.12195H17.93L16.76 17.5283C16.6702 18.479 16.238 19.3612 15.5477 20.0031C14.8573 20.645 13.9583 21.0004 13.026 21H6.974C6.04186 21.0001 5.1431 20.6446 4.45295 20.0027C3.7628 19.3609 3.33073 18.4788 3.241 17.5283L2.07 5.12195H0.75C0.551088 5.12195 0.360322 5.04101 0.21967 4.89692C0.0790175 4.75284 0 4.55742 0 4.35366C0 4.14989 0.0790175 3.95448 0.21967 3.81039C0.360322 3.66631 0.551088 3.58537 0.75 3.58537H6.5ZM8.5 8.45122C8.5 8.24746 8.42098 8.05204 8.28033 7.90795C8.13968 7.76387 7.94891 7.68293 7.75 7.68293C7.55109 7.68293 7.36032 7.76387 7.21967 7.90795C7.07902 8.05204 7 8.24746 7 8.45122V16.1341C7 16.3379 7.07902 16.5333 7.21967 16.6774C7.36032 16.8215 7.55109 16.9024 7.75 16.9024C7.94891 16.9024 8.13968 16.8215 8.28033 16.6774C8.42098 16.5333 8.5 16.3379 8.5 16.1341V8.45122ZM12.25 7.68293C12.0511 7.68293 11.8603 7.76387 11.7197 7.90795C11.579 8.05204 11.5 8.24746 11.5 8.45122V16.1341C11.5 16.3379 11.579 16.5333 11.7197 16.6774C11.8603 16.8215 12.0511 16.9024 12.25 16.9024C12.4489 16.9024 12.6397 16.8215 12.7803 16.6774C12.921 16.5333 13 16.3379 13 16.1341V8.45122C13 8.24746 12.921 8.05204 12.7803 7.90795C12.6397 7.76387 12.4489 7.68293 12.25 7.68293Z";

    public static SVGPath createTrashSVGPath() {
        SVGPath trashSVGPath = new SVGPath();
        trashSVGPath.setContent(SvgIcons.TRASH_SVG_PATH);
        trashSVGPath.setStrokeWidth(1);
        trashSVGPath.setScaleX(0.7);
        trashSVGPath.setScaleY(0.7);
        return trashSVGPath;
    }

    public static SVGPath createPinpointSVGPath() {
        SVGPath trashSVGPath = new SVGPath();
        trashSVGPath.setContent("M5.5 7.68117C5.87813 7.68117 6.20194 7.54035 6.47144 7.25869C6.74048 6.97751 6.875 6.63934 6.875 6.24416C6.875 5.84898 6.74048 5.51056 6.47144 5.22891C6.20194 4.94773 5.87813 4.80714 5.5 4.80714C5.12187 4.80714 4.79829 4.94773 4.52925 5.22891C4.25975 5.51056 4.125 5.84898 4.125 6.24416C4.125 6.63934 4.25975 6.97751 4.52925 7.25869C4.79829 7.54035 5.12187 7.68117 5.5 7.68117ZM5.5 14.5968C5.40833 14.5968 5.31667 14.5788 5.225 14.5429C5.13333 14.507 5.05312 14.4591 4.98438 14.3992C3.31146 12.8544 2.0625 11.4205 1.2375 10.0975C0.4125 8.77402 0 7.53747 0 6.38786C0 4.59159 0.552979 3.16056 1.65894 2.09477C2.76444 1.02899 4.04479 0.496094 5.5 0.496094C6.95521 0.496094 8.23556 1.02899 9.34106 2.09477C10.447 3.16056 11 4.59159 11 6.38786C11 7.53747 10.5875 8.77402 9.7625 10.0975C8.9375 11.4205 7.68854 12.8544 6.01562 14.3992C5.94688 14.4591 5.86667 14.507 5.775 14.5429C5.68333 14.5788 5.59167 14.5968 5.5 14.5968Z");
        trashSVGPath.setStrokeWidth(1);
        return trashSVGPath;
    }

    public static SVGPath createCreditCardPath() {
        SVGPath trashSVGPath = new SVGPath();
        trashSVGPath.setContent("M1.9 12C1.3775 12 0.930367 11.8533 0.5586 11.5597C0.1862 11.2657 0 10.9125 0 10.5V1.5C0 1.0875 0.1862 0.7345 0.5586 0.441C0.930367 0.147 1.3775 0 1.9 0H17.1C17.6225 0 18.07 0.147 18.4424 0.441C18.8141 0.7345 19 1.0875 19 1.5V10.5C19 10.9125 18.8141 11.2657 18.4424 11.5597C18.07 11.8533 17.6225 12 17.1 12H1.9ZM1.9 6H17.1V3H1.9V6Z");
        trashSVGPath.setStrokeWidth(1);
        return trashSVGPath;
    }

    }