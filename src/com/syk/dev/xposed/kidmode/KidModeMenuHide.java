package com.syk.dev.xposed.kidmode;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

/**
 * Xposed module that hides Kid Mode option from power menu
 * 
 * Created by Steven on 10/6/13.
 */
public class KidModeMenuHide implements IXposedHookLoadPackage{
	
	/**
	 * Hook the android process
	 */
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.processName.equals("android"))
            return;

        XposedBridge.log("Adding hooks for process " + lpparam.processName);
        
        findAndHookMethod("com.android.internal.policy.impl.GlobalActions", lpparam.classLoader, "supportKidModeApp", "android.content.Context", new XC_MethodReplacement() {
			@Override
			protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
				return false;
			}
        });
    }
}
