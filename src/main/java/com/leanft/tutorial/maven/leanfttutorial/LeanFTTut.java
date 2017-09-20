package com.leanft.tutorial.maven.leanfttutorial;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.web.RadioGroup;
import com.hp.lft.sdk.web.Browser;
import com.hp.lft.sdk.web.BrowserFactory;
import com.hp.lft.sdk.web.BrowserType;
import com.hp.lft.sdk.web.Button;
import com.hp.lft.sdk.web.ButtonDescription;
import com.hp.lft.sdk.web.EditField;
import com.hp.lft.sdk.web.EditFieldDescription;
import com.hp.lft.sdk.web.ImageDescription;
import com.hp.lft.sdk.web.Image;
import com.hp.lft.sdk.web.Link;
import com.hp.lft.sdk.web.LinkDescription;
import com.hp.lft.sdk.web.ListBox;
import com.hp.lft.sdk.web.ListBoxDescription;
import com.hp.lft.sdk.web.RadioGroupDescription;
import com.hp.lft.sdk.web.Table;
import com.hp.lft.verifications.Verify;
import com.leanft.tutorial.maven.leanfttutorial.unittesting.UnitTestClassBase;
import com.hp.lft.sdk.web.TableCell;
import com.hp.lft.sdk.web.TableDescription;
import com.hp.lft.sdk.web.TableRow;

public class LeanFTTut extends UnitTestClassBase{
		
		private Browser browser;

	    public LeanFTTut() {}

	    @BeforeClass
	    public static void setUpBeforeClass() throws Exception {
	        instance = new LeanFTTut();
	        globalSetup(LeanFTTut.class);
	    }

	    @AfterClass
	    public static void tearDownAfterClass() throws Exception {
	        globalTearDown();
	    }

	    @Before
	    public void setUp() throws Exception {
	    	browser = BrowserFactory.launch(BrowserType.FIREFOX);
	    	//browser = BrowserFactory.launch(BrowserType.CHROME);
	    	browser.deleteCookies();
	    }

	    @After
	    public void tearDown() throws Exception {
	    	browser.close();
	    }
	    
	    @Ignore
	    @Test
	    public void test() throws GeneralLeanFtException {
	    	browser.navigate("https://www.google.ro/");
	    	browser.sync();
	    	Verify.contains(browser.getURL(),"www.google.ro");
	    	EditField searchText = browser.describe(EditField.class, new EditFieldDescription.Builder()
	    			.type("text")
	    			.tagName("INPUT")
	    			.name("q").build());
	    	searchText.setValue("http://www.euro-testing.com/");
	    	/*
	    	Button googleSearch = browser.describe(Button.class, new ButtonDescription.Builder()
	    			.buttonType("submit")
	    			.tagName("INPUT")
	    			.name("Google Search").build());
	    			*/
	    	Button googleSearch = browser.describe(Button.class, new ButtonDescription.Builder()
	    											.xpath("/html/body/div/div[3]/form/div[2]/div[3]/center/input[1]")
	    											.visible(true).build());
	    	Verify.isTrue(googleSearch.exists());
	    	googleSearch.click();
	    	browser.sync();
	    	Link firstLink = browser.describe(Link.class, new LinkDescription.Builder()
	    			.tagName("A")
	    			.innerText("Home - Euro Testing Software Solutions").build());
	    	System.out.println(">>>"+firstLink.getInnerText()+"<<<");
	    	Verify.contains(firstLink.getInnerText(), "Euro");
	    	firstLink.click();
	    	browser.sync();
	    	Image euroSigla = browser.describe(Image.class, new ImageDescription.Builder()
	    			.alt("Euro Testing")
	    			.type(com.hp.lft.sdk.web.ImageType.LINK)
	    			.tagName("IMG")
	    			.index(0).build());
	    	Verify.isTrue(euroSigla.exists());
	    }
	    
	    @Test
	    public void testNewTours(){
	    	
	    	try{
	    		browser.navigate("http://newtours.demoaut.com/mercurywelcome.php");
	    		browser.sync();
	    		Image mercuryTourImg = browser.describe(Image.class, new ImageDescription.Builder()
	    				.alt("Mercury Tours")
	    				.type(com.hp.lft.sdk.web.ImageType.NORMAL)
	    				.tagName("IMG").build());
	    		Verify.isTrue(mercuryTourImg.exists());
	    		EditField  userName = browser.describe(EditField.class, new EditFieldDescription.Builder()
	    				.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[3]/form/table/tbody/tr[4]/td/table/tbody/tr[2]/td[2]/input")
	    				.visible(true).build());
	    		Verify.isTrue(userName.exists());
	    		userName.setValue("tutorial");
	    		EditField  password = browser.describe(EditField.class, new EditFieldDescription.Builder()
	    											.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[3]/form/table/tbody/tr[4]/td/table/tbody/tr[3]/td[2]/input")
	    											.visible(true).build());
	    		Verify.isTrue(password.exists());
	    		password.setValue("tutorial");
	    		Image singIn = browser.describe(Image.class, new ImageDescription.Builder()
				    										  .xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[3]/form/table/tbody/tr[4]/td/table/tbody/tr[4]/td[2]/div/input")
				    										  .visible(true).build());
	    		Verify.isTrue(singIn.exists());
	    		singIn.click();
	    		browser.sync();
	    		
	    		//page 2
	    		
	    		RadioGroup types = browser.describe(RadioGroup.class, new RadioGroupDescription.Builder()
	    											.name("tripType")
	    											.visible(true).build());
	    		Verify.isTrue(types.exists());
	    		types.select(0);
	    		ListBox passangers = browser.describe(ListBox.class, new ListBoxDescription.Builder()
	    												.name("passCount")
	    												.visible(true).build());
	    		Verify.isTrue(passangers.exists());
	    		Verify.isTrue(passangers.isEnabled());
	    		passangers.select(1);
	    		ListBox from = browser.describe(ListBox.class, new ListBoxDescription.Builder()
						.name("fromPort")
						.visible(true).build());
	    		Verify.isTrue(from.exists());
	    		Verify.isTrue(from.isEnabled());
	    		from.select(1);
	    		ListBox onMonth = browser.describe(ListBox.class, new ListBoxDescription.Builder()
						.name("fromMonth")
						.visible(true).build());
	    		Verify.isTrue(onMonth.exists());
	    		Verify.isTrue(onMonth.isEnabled());
	    		onMonth.select(8);
	    		ListBox onDay = browser.describe(ListBox.class, new ListBoxDescription.Builder()
						.name("fromDay")
						.visible(true).build());
	    		Verify.isTrue(onDay.exists());
	    		Verify.isTrue(onDay.isEnabled());
	    		onDay.select(9);
	    		ListBox to = browser.describe(ListBox.class, new ListBoxDescription.Builder()
						.name("toPort")
						.visible(true).build());
	    		Verify.isTrue(to.exists());
	    		Verify.isTrue(to.isEnabled());
	    		to.select(0);
	    		ListBox returningMonth = browser.describe(ListBox.class, new ListBoxDescription.Builder()
						.name("toMonth")
						.visible(true).build());
	    		Verify.isTrue(returningMonth.exists());
	    		Verify.isTrue(returningMonth.isEnabled());
	    		returningMonth.select(8);
	    		ListBox returningDay = browser.describe(ListBox.class, new ListBoxDescription.Builder()
						.name("toDay")
						.visible(true).build());
	    		Verify.isTrue(returningDay.exists());
	    		Verify.isTrue(returningDay.isEnabled());
	    		returningDay.select(19);
	    		RadioGroup serviceClass = browser.describe(RadioGroup.class, new RadioGroupDescription.Builder()
						.name("servClass")
						.visible(true).build());
	    		Verify.isTrue(serviceClass.exists());
	    		Verify.isTrue(serviceClass.isEnabled());
	    		serviceClass.select(2);
	    		ListBox airline = browser.describe(ListBox.class, new ListBoxDescription.Builder()
						.name("airline")
						.visible(true).build());
	    		Verify.isTrue(airline.exists());
	    		Verify.isTrue(airline.isEnabled());
	    		airline.select(3);
	    		Image cont= browser.describe(Image.class, new ImageDescription.Builder()
						.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[14]/td/input")
						.visible(true).build());
	    		Verify.isTrue(cont.exists());
	    		cont.click();
	    		browser.sync();
	    		
	    		//page 3
	    		
	    		Image selectFlight = browser.describe(Image.class, new ImageDescription.Builder()
						  .xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[1]/td/img")
						  .visible(true).build());
	    		Verify.isTrue((selectFlight.exists()));
	    		
	    		RadioGroup outFlight = browser.describe(RadioGroup.class, new RadioGroupDescription.Builder()
						.name("outFlight")
						.visible(true).build());
	    		Verify.isTrue(outFlight.exists());
	    		outFlight.select(2);
	    		RadioGroup inFlight = browser.describe(RadioGroup.class, new RadioGroupDescription.Builder()
						.name("inFlight")
						.visible(true).build());
	    		Verify.isTrue(inFlight.exists());
	    		inFlight.select(2);
	    		Table departureTable = browser.describe(Table.class, new TableDescription.Builder()
	    																	.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table[1]")
	    																	.visible(true).build());
	    		List<TableRow> rowsDep = departureTable.getRows();
	    		List<TableCell> cellsDep = rowsDep.get(2).getCells();
	    		Verify.areEqual("9:17", cellsDep.get(2).getText());
	    		
	    		Table arivalsTable = browser.describe(Table.class, new TableDescription.Builder()
						.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table[2]")
						.visible(true).build());
	    		List<TableRow> rowsArival = departureTable.getRows();
	    		List<TableCell> cellsArival = rowsDep.get(3).getCells();
	    		Verify.areEqual("16:37", cellsDep.get(2).getText());
	    		
	    		Image contPage3= browser.describe(Image.class, new ImageDescription.Builder()
						.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/p/input")
						.visible(true).build());
	    		Verify.areEqual("reserveFlights", contPage3.getTagName());
	    		contPage3.click();
	    		browser.sync();
	    		
	    		//page4
	    		
	    		Image bookAFlightPage4= browser.describe(Image.class, new ImageDescription.Builder()
						.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[1]/td/img")
						.visible(true).build());
	    		
	    		Verify.isTrue(bookAFlightPage4.exists());
	    		
	    		Table summaryTable = browser.describe(Table.class, new TableDescription.Builder()
						.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td/table")
						.visible(true).build());
	    		Verify.isTrue(summaryTable.exists());
	    		List<TableRow> rowsSummary = departureTable.getRows();
	    		List<TableCell> cellsSummary = rowsDep.get(3).getCells();
	    		System.out.println(">>>cellsSummary.get(0).getText()="+cellsSummary.get(0).getText()+"<<<");
	    		Verify.areEqual("274", cellsSummary.get(0).getText());
	    		List<TableCell> cellsSummary2 = rowsDep.get(6).getCells();
	    		Verify.areEqual("282", cellsSummary2.get(2).getText());
	    		List<TableCell> cellsSummary3 = rowsDep.get(9).getCells();
	    		Verify.areEqual("$1203", cellsSummary3.get(1).getText());
	    	}catch(Exception ge){
	    		ge.printStackTrace();
	    		Assert.fail();
	    	}
	    }
	    
}
