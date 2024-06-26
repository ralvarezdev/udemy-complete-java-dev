package oop.encapsulation;

public class Printer {
    private int tonerLevel;
    private int pagesPrinted;
    private final boolean duplex;

    public Printer(int tonerLevel, boolean duplex) {
        super();
        this.tonerLevel = tonerLevel > -1 && tonerLevel <= 100 ? tonerLevel : -1;
        this.pagesPrinted = 0;
        this.duplex = duplex;
    }

    public int getPagesPrinted() {
        return pagesPrinted;
    }

    public int addToner(int tonerAmount) {
        if (tonerLevel == -1 || tonerAmount <= 0 || tonerAmount > 100)
            return -1;

        if (tonerLevel + tonerAmount > 100)
            return -1;

        tonerLevel += tonerAmount;

        return tonerLevel;
    }

    public int printPages(int pagesToPrint) {
        int tempPages = (duplex) ? pagesToPrint / 2 + pagesToPrint % 2 : pagesToPrint;
        pagesPrinted += tempPages;
        return tempPages;
    }
}