package com.jshs.mobile.banmen.Models;

import android.app.Application;
import android.content.Context;

import com.jshs.mobile.banmen.Tools.MLOG_LEVEL;
import com.jshs.mobile.banmen.Tools.MLog;

import org.xutils.DbManager;
import org.xutils.DbManager.DaoConfig;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.List;
import java.util.Objects;

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

    public String TAG() {
        return getClass().getSimpleName();
    }

    public static void setContext(Application ctx) {
        _context = ctx;
    }

    private DataBase(OnDbUpgradeListener listener) {
        _dbManager = x.getDb(getDbConfigs());
        _listener = listener;
    }

    public static DataBase getInstance() {
        return getInstance(null);
    }

    public static DataBase getInstance(OnDbUpgradeListener listener) {
        if (_dateBase == null) {
            _dateBase = new DataBase(listener);
        }
        return _dateBase;
    }


    public DbManager getDbManager() {
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

    public <T> void save(T entity) {
        if (entity != null) {
            try {
                _dbManager.save(entity);
            } catch (Exception e) {
                MLog.print(TAG(), MLOG_LEVEL.E, e.getMessage());
            }
        } else {
            MLog.print(TAG(), MLOG_LEVEL.E, "save NULL");
        }
    }

    public <T> void delete(T entity) {
        if (entity != null) {
            try {
                _dbManager.delete(entity);
            } catch (Exception e) {
                MLog.print(TAG(), MLOG_LEVEL.E, e.getMessage());
            }
        } else {
            MLog.print(TAG(), MLOG_LEVEL.E, "delete NULL");
        }
    }

    public <T> T selectFrist(Class<T> classOfT) {
        T t = null;
        try {
            t = _dbManager.selector(classOfT).findFirst();
        } catch (Exception e) {
            MLog.print(TAG(), MLOG_LEVEL.E, e.getMessage());
        }
        return t;
    }

    public <T> List<T> selectAll(Class<T> classOfT) {
        List<T> t = null;
        try {
            t = _dbManager.selector(classOfT).findAll();
        } catch (Exception e) {
            MLog.print(TAG(), MLOG_LEVEL.E, e.getMessage());
        }
        return t;
    }

    public <T> T selectById(Class<T> classOfT, Object id) {
        T t = null;
        try {
            t = _dbManager.findById(classOfT, id);
        } catch (Exception e) {
            MLog.print(TAG(), MLOG_LEVEL.E, e.getMessage());
        }
        return t;
    }

    public <T> void deleteById(Class<T> classOfT, Object id) {
        try {
            _dbManager.deleteById(classOfT, id);
        } catch (Exception e) {
            MLog.print(TAG(), MLOG_LEVEL.E, e.getMessage());
        }
    }

    public <T> void dropTable(Class<T> classOfT) {
        try {
            _dbManager.dropTable(classOfT);
        } catch (Exception e) {
            MLog.print(TAG(), MLOG_LEVEL.E, e.getMessage());
        }
    }

    public <T> void saveOrUpdate(T entity) {
        try {
            _dbManager.saveOrUpdate(entity);
        } catch (Exception e) {
            MLog.print(TAG(), MLOG_LEVEL.E, e.getMessage());
        }
    }

}