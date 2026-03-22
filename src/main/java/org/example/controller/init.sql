-- SQLite 语法
-- 1. 创建用户表
CREATE TABLE IF NOT EXISTS user (
                                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                                    account TEXT UNIQUE NOT NULL,
                                    password TEXT NOT NULL,
                                    role INTEGER NOT NULL, -- 1=学生，2=管理员
                                    name TEXT,
                                    dorm_building TEXT,
                                    dorm_room TEXT,
                                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 2. 创建报修单表
CREATE TABLE IF NOT EXISTS repair_order (
                                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                                            student_id INTEGER NOT NULL,
                                            building TEXT,
                                            room TEXT,
                                            device_type TEXT,
                                            description TEXT,
                                            status INTEGER DEFAULT 1, -- 1=待处理，2=处理中，3=已完成
                                            created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                            updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);