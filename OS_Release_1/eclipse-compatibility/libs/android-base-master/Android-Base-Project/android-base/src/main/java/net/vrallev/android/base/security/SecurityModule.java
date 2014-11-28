package net.vrallev.android.base.security;

import android.content.Context;

import net.vrallev.android.base.ForApplication;
import net.vrallev.android.base.security.keychain.BaseKeyChain;
import net.vrallev.android.base.security.keychain.SharedPreferencesKeyChain;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf Wondratschek
 */
@SuppressWarnings("UnusedDeclaration")
@Module(
        library = true,
        complete = false
)
public class SecurityModule {

    private final boolean mUseConceal;

    public SecurityModule() {
        mUseConceal = hasConcealInClasspath();
    }

    private boolean hasConcealInClasspath() {
        try {
            Class.forName("com.facebook.crypto.Crypto");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }


    @Provides
    @Singleton
    BaseKeyChain provideKeyChain(@ForApplication Context context) {
        if (mUseConceal) {
            return null;
        } else {
            return new SharedPreferencesKeyChain(context);
        }
    }

    @Provides
    @Singleton
    CipherTool provideCipherTool(BaseKeyChain keyChain) {
        if (mUseConceal) {
            return null;
        } else {
            return new BouncyCastleCipherTool(keyChain.getPassPhrase(), keyChain.getSalt());
        }
    }
}
