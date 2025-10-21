# TripNTreat
## 旅遊專案
### 行程服務訂購
#### 適合親子共遊


## 專案簡介
#### 旅遊行程訂購平台，包含會員與後台管理，完整實現商品管理與訂單處理流程。


## 會員/員工登入
#### 分頁式 UI
#### 權限分為會員與管理員（員工）
#### 登入後自動導向對應會員及員工入口


## 會員
#### 會員註冊與登入
#### 商品瀏覽與搜尋
#### 查看訂單紀錄
#### 修改會員資料


## 員工後台管理
#### 商品管理：新增、修改商品分類與商品、調整商品價格
#### 會員管理：檢視與編輯會員資料
#### 訂單管理：查詢所有訂單記錄


## 會員訂購頁
#### 商品瀏覽與搜尋
#### 加入訂單
#### 確認與下單
#### 找零
#### 登出平台


## 使用技術
#### Java（JDK 11）
#### JDBC
#### MVC 架構
#### JFrame GUI
#### MySQL 資料庫

## 專案架構



## 操作說明
#### 安裝 JDK 11 並設定環境變數
#### 使用 MySQL 建立資料庫，執行 sql/setup.sql
#### 執行jar檔案
#### 設定好資料庫連線Dbconnection
#### 登入後可使用會員或員工後台管理功能




ProjectRoot/
├── src/main/java/              // 核心 Java 程式碼
│
│   ├── config/                     // 系統配置套件
│   │   └── AppConfig.java          // 應用程式常數、初始化設定等
│   │
│   ├── controller/                 // 控制器套件 (View 和 Service 之間的橋樑)
│   │   ├── EmployeeController.java     // 處理員工UI互動邏輯
│   │   ├── MemberController.java       // 處理會員UI互動邏輯
│   │   ├── TravelPlanController.java   // 處理行程UI互動邏輯
│   │   ├── ServiceController.java      // 處理服務UI互動邏輯
│   │   ├── OrderMasterController.java  // 處理訂單主表UI互動邏輯
│   │   └── OrderDetailController.java  // 處理訂單明細UI互動邏輯 (包含 JFreeChart/POI 邏輯)
│   │
│   ├── exception/                  // 自訂例外處理套件
│   │   ├── DataAccessException.java    // 資料庫操作異常
│   │   └── BusinessException.java      // 業務邏輯異常 (如庫存不足、重複新增)
│   │
│   ├── po/                         // 持久化物件套件 (Persistent Object - 與資料庫表結構高度一致)
│   │   ├── EmployeePo.java             // 員工資料模型
│   │   ├── MemberPo.java               // 會員資料模型
│   │   ├── TravelPlanPo.java           // 行程資料模型
│   │   ├── ServicePo.java              // 服務項目資料模型
│   │   ├── OrderMasterPo.java          // 訂單主表資料模型 (多對多)
│   │   └── OrderDetailPo.java          // 訂單明細資料模型
│   │
│   │   ├── po.dao/                 // PO 的 資料存取物件 (Data Access Object) 介面
│   │   │   ├── EmployeeDao.java
│   │   │   ├── MemberDao.java
│   │   │   ├── ... (其他模組的 Dao 介面)

│   │   │   └── impl/               // Dao 介面的實作
│   │   │       ├── EmployeeDaoImpl.java
│   │   │       ├── MemberDaoImpl.java
│   │   │       └── ... (其他模組的 DaoImpl)
│   │   │
│   │   └── po.service/             // PO 的 業務邏輯服務 (Service) 介面
│   │       ├── EmployeeService.java
│   │       ├── MemberService.java
│   │       ├── ... (其他模組的 Service 介面)
│   │       └── impl/               // Service 介面的實作
│   │           ├── EmployeeServiceImpl.java
│   │           ├── MemberServiceImpl.java
│   │           └── ... (其他模組的 ServiceImpl)
│   │
│   ├── vo/                         // 值物件套件 (Value Object - 數據傳輸與介面顯示)
│   │   ├── EmployeeVo.java             // 員工展示數據 (可能不含密碼等敏感欄位)
│   │   ├── OrderReportVo.java          // 訂單報表彙總數據 (用於 POI)
│   │   ├── ChartDataVo.java            // JFreeChart 圖表所需的數據結構
│   │   └── ComboItemVo.java            // JComboBox 下拉選單項目通用結構
│   │
│   │   ├── vo.dao/                 // VO 在 DAO 層輔助的數據結構介面 (較少用，通常用於聚合查詢結果)
│   │   │   ├── MonthlySalesVo.java
│   │   │   └── impl/
│   │   │       └── (專門的 VO 實作，如果需要)
│   │   │
│   │   └── vo.service/             // VO 在 Service 層輔助的數據結構介面 (常用於傳遞複雜的業務數據)
│   │       ├── OrderProcessVo.java     // 訂單處理流程中的數據包
│   │       └── impl/
│   │           └── (專門的 VO 實作，如果需要)
│   │
│   └── util/                       // 通用工具套件
│       ├── DbConnection.java           // 資料庫連線管理 (負責連線池或單例連線)
│       └── Tool.java                   // 通用工具類 (日期格式化、字串處理、驗證)
│
└── src/main/resources/         // 資源檔 (如 log4j 配置、i18n 檔)



| 第一階段                |   第二階段       | 第三階段           | 第四階段          | 第五階段        | 
| ----------------------- | ---------------| ------------------ |------------------|----------------|
| 平台構思                |  分析資料庫表單  | 介面對應資料庫      | 測試資料寫入資料庫 | 確認平台功能    |


- 編號 
- 分點
<img width="680" height="362" alt="WelcomePage" src="https://github.com/user-attachments/assets/483f7012-d5ee-4abb-92ca-070963331d95" />
