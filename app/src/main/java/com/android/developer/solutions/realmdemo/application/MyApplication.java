package com.android.developer.solutions.realmdemo.application;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.android.developer.solutions.realmdemo.Util;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.io.File;
import java.security.SecureRandom;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MyApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        // Generate a key
        // IMPORTANT! This is a silly way to generate a key. It is also never stored.
        // For proper key handling please consult:
        // * https://developer.android.com/training/articles/keystore.html
        // * http://nelenkov.blogspot.dk/2012/05/storing-application-secrets-in-androids.html
        byte[] key = new byte[64];
        new SecureRandom().nextBytes(key);

        Realm.init(this);
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("landmatric.realm")
                .encryptionKey(key)
                .schemaVersion(2)
                //.modules(new MySchemaModule())
                //.migration(new MyMigration())
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        //method to generate realm db in external storage
        //initializeRealm();
    }

    private void initializeRealm() {

        Realm.init(this);

        RealmConfiguration.Builder realmBuilder = new RealmConfiguration.Builder();
        realmBuilder.directory(new File(getRealmPath(this)));
        realmBuilder.name("realmDataBase.realm");

        RealmConfiguration config = realmBuilder.build();
        Realm.setDefaultConfiguration(config);
    }

    public String getRealmPath(Context context) {

        String externalStorageDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        String packageName = context.getApplicationContext().getPackageName();
        return externalStorageDir + File.separator + "RealmDemo" + File.separator;
    }
}
