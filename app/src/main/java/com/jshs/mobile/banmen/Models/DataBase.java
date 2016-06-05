package com.jshs.mobile.banmen.Models;

import android.app.Application;
import android.content.Context;

import org.xutils.DbManager;
import org.xutils.DbManager.DaoConfig;
import org.xutils.x;

/**
 * Created by popze on 2016/6/6.
 */
public class DataBase {
    private static String DB_NAME = "localdate.db";
    private static int DB_VERSION = 1;
    private static DataBase _dateBase;
    private static Context _context;

    private DaoConfig _dbConfigs;
    private DbManager _dbManager;
    private OnDbUpgradeListener _listener;

    public static void setContext(Application ctx) {
        _context = ctx;
    }

    private DataBase(OnDbUpgradeListener listener) {
        _dbManager = x.getDb(getDbConfigs());
        _listener = listener;
    }

    public DbManager getInstance() {
        return getInstance(null);
    }

    public static DbManager getInstance(OnDbUpgradeListener listener) {
        if (_dateBase == null) {
            _dateBase = new DataBase(listener);
        }
        return _dateBase.getDbManager();
    }


    private DbManager getDbManager() {
        return _dbManager;
    }

    public DaoConfig getDbConfigs() {
        if (_dbConfigs == null) {
            _dbConfigs = new DbManager.DaoConfig().setDbName(DB_NAME).setDbVersion(DB_VERSION).setDbUpgradeListener(dbUpgradeListener).setDbOpenListener(new DbManager.DbOpenListener() {
                @Override
                public void onDbOpened(DbManager db) {
                    db.getDatabase().enableWriteAheadLogging();
                }
            });
        }
        return _dbConfigs;
    }


    private DbManager.DbUpgradeListener dbUpgradeListener = new DbManager.DbUpgradeListener() {
        @Override
        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

            if (_listener != null) {
                _listener.onDbUpgradeComplete(db);
                _listener = null;
            }
        }
    };

    public interface OnDbUpgradeListener {
        void onDbUpgradeComplete(DbManager dbManager);
    }

}