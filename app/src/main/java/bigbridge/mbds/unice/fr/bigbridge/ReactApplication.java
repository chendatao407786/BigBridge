package bigbridge.mbds.unice.fr.bigbridge;

import android.app.Application;

import com.BV.LinearGradient.LinearGradientPackage;
import com.airbnb.android.react.maps.MapsPackage;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.airbnb.android.react.maps.MapsPackage;
import java.util.Arrays;
import java.util.List;

public class ReactApplication extends Application implements com.facebook.react.ReactApplication{
    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return true;
        }

        @Override
        public List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
                    new MapsPackage(),
                    new LinearGradientPackage()
            );
        }
    };
    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }


}
