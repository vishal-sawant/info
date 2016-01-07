package com.pack.common.pageobjects;

import org.openqa.selenium.WebDriver;

import com.pack.base.CommonMethods;
import com.pack.base.LaunchBrowser;

public class ArticlePage {
	private WebDriver driver;
	protected CommonMethods commonObj = LaunchBrowser.getCommonMethodObj();

	public ArticlePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getArticleID() {
		String articleURL = commonObj.getPageUrl();
		String articleID = articleURL.split("=")[1];
		return articleID;
	}
	
	public boolean verifySectionsOnArticlePage()
			throws Exception {
		String sectionText;
		String articleSections[] = new String[] { "topStoriesText",
				"yourCompanysConnectionText", "yourConnectionText",
				"relatedTopicsText", "relatedIndustriesText",
				"companiesMentionedText" };
		String sectionTextToVerify[] = new String[] { "Today's Top Stories",
				"Your Company's Connections", "Your Connections",
				"Related Topics", "Related Industries", "Companies Mentioned" };
		for (int i = 0; i < 6; i++) {
			sectionText = commonObj.getWebElement(articleSections[i])
					.getText();

			if (!sectionText.equalsIgnoreCase(sectionTextToVerify[i])) {
				return false;
			}
		}
		return true;
	}

}
