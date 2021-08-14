package com.div.proj.utilities;

import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

public class SimpleLoggingAssert extends SoftAssert {

	  @Override
	  public void onAssertSuccess(IAssert<?> assertCommand) {
	    System.err.println(assertCommand.getMessage() + " <PASSED> ");
	  }

	  @Override
	  public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
	    String suffix =
	        String.format(
	            "Expected [%s] but found [%s]",
	            assertCommand.getExpected().toString(), assertCommand.getActual().toString());
	    System.err.println(assertCommand.getMessage() + " <FAILED>. " + suffix);
	  }
	}
