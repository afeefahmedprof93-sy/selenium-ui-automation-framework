package com.orange.automation.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.orange.automation.base.BasePage;

public class Listeners implements ITestListener{

    public static int passedTestCasesCount = 0;

    public static int failedTestCasesCount = 0;

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        BasePage.stepFailed("Assertion Faild. Found result: " + iTestResult.getThrowable());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        failedTestCasesCount = iTestContext.getFailedTests().size();
        passedTestCasesCount = iTestContext.getPassedTests().size();
    }
    
}
