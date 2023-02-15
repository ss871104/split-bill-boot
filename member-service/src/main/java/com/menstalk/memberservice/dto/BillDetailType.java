package com.menstalk.memberservice.dto;

	/**
	 * 在 Java 程式語言中，Enum（列舉）是一種特殊的類別，可以定義一些固定的常數值，並且列舉型別具有限定的功能，
	 * 例如只能選取列舉內定義的值，可以使用 switch case 結構來處理列舉值，以及可以在集合內存放列舉值等。
	 * 上述程式碼定義了一個名為 BillDetailType 的列舉型別，它內部定義了兩個常數值：INCOME 和 EXPENSE，
	 * 這個列舉型別表示的是帳單細節的類型，INCOME 表示收入類型，EXPENSE 表示支出類型。在程式開發中，
	 * 這個列舉型別可以用來區分不同類型的帳單細節，方便代碼的管理和維護。
	 * @author user
	 *
	 */
public enum BillDetailType {
	INCOME, EXPENSE,

}
