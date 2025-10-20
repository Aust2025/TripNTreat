# TripNTreat
## 旅遊專案
### 行程服務訂購
#### 適合親子共遊


本專案採用 Java 語言 開發，整合 Eclipse IDE 及 Maven 專案管理，遵循 MVC (Model-View-Controller) + DAO (Data Access Object) 的架構原則，並使用 MySQL 作為資料庫。

1. 開發環境與工具
項目	說明
程式語言	Java
整合開發環境 (IDE)	Eclipse
專案管理工具	Maven
UI 開發	Eclipse WindowBuilder (Swing)
資料庫	MySQL
2. Maven 依賴 (pom.xml)
專案需包含以下核心依賴：

依賴名稱	用途
mysql-connector-java	MySQL 資料庫連接
poi / poi-ooxml	處理 Excel 文件 (訂單明細的報表匯出)
jfreechart	繪製圖表 (訂單明細的視覺化分析)
jcommon	jfreechart 依賴
3. UI 介面設計
UI 技術: Swing (使用 Eclipse WindowBuilder 輔助設計)

Controller/View 核心: JFrame

常用元件:

JTable: 用於顯示員工、會員、行程、服務等列表數據。

JComboBox: 用於提供下拉選單選擇 (如區域選擇、行程名稱選擇等)。

4. 專案結構 (Package Structure)
專案結構嚴格遵循 MVC+DAO 原則，並將 Model 層細分為 PO (Persistent Object) 和 VO (Value Object) 兩個層次。

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

架構說明：

PO (Persistent Object): 專注於資料持久化，通常與資料庫表結構一對一對應，用於 DAO 層的存取。

VO (Value Object): 專注於業務數據的傳輸和展示，可能聚合多個 PO 的數據，或只包含 PO 的部分欄位 (如用於 View 層的資料顯示)。

DAO (Data Access Object): 負責資料庫的 CRUD (Create, Read, Update, Delete) 操作。

Service: 負責 業務邏輯 的處理，協調多個 DAO 操作，並處理交易 (Transaction)。

Controller: 處理 UI 介面的事件，調用 Service 層的方法，並更新 View 層。

5. 模組列表與核心功能
模組名稱	核心功能
員工模組	CRUD (新增/查詢/修改/刪除) 員工資料、登入驗證。
會員模組	CRUD 會員資料、積分管理、等級劃分。
行程模組	包含：目的地、區域、行程名稱、行程庫存、單價。CRUD 行程資料，庫存管理。
服務模組	包含：訂計程車、行李、飯店餐廳項目、單價、數量。CRUD 服務項目，服務單價設定。
訂單主表模組	多對多 關係管理 (與行程/服務的關係)。生成訂單、狀態追蹤。
訂單明細表模組	記錄訂單中包含的行程和服務明細。

5.1 訂單明細的報表與圖表功能

功能	模組/技術	說明
報表匯出	POI	使用 Apache POI 實現，將訂單明細、銷售統計等數據導出為 Excel (.xlsx) 文件，供財務或管理層分析。
數據視覺化	JFreeChart	在 UI 介面中嵌入圖表
	
  
📁 各層職責說明
1️⃣ controller — 控制層 (MVC 的 C)
負責接收使用者請求，呼叫 service 處理業務邏輯，最後返回結果（頁面或 JSON）。
依模組分為：
employee：員工相關操作（登入、報表、更新員工資料等）。
member：會員相關操作（註冊、登入、修改資料）。
trorder：旅遊訂單相關操作（新增、查詢、刪除、輸出）。
代表檔案：
EmployeeLogin.java, OrderReport.java, AddMember.java, AddTrorder.java 等。


2️⃣ service — 業務邏輯層
處理具體業務邏輯，調用 DAO 操作資料庫。
對應多個實體：Employee, Member, Trip, Trorder 等。
代表檔案：
Employee.java, Member.java, Trip.java, Trorder.java

3️⃣ dao — 資料訪問層
定義資料庫操作介面 (Dao)，以及具體實作 (DaoImpl)。
負責與資料庫互動，如新增、刪除、查詢、更新等。

dao
├── EmployeeDao.java
├── MemberDao.java
├── ServiceDao.java
├── TripDao.java
├── TrorderDao.java
├── TrorderDetailDao.java
└── impl
    ├── EmployeeDaoImpl.java
    ├── MemberDaoImpl.java
    ├── ServiceDaoImpl.java
    ├── TripDaoImpl.java
    ├── TrorderDaoImpl.java
    └── TrorderDetailDaoImpl.java
    
4️⃣ po — 實體 (Persistence Object)
與資料庫表對應的 Java 類別，用於封裝資料。
範例：
Employee.java
Member.java
Trip.java
Trorder.java
TrorderDetail.java

5️⃣ util — 工具類
封裝共用功能，如資料庫連線、Excel 匯出、輸入驗證、格式轉換等。
代表檔案：
DbConnection.java：資料庫連線。
CreateExcel.java：建立 Excel 報表。
ToolChange.java, ChartUtil.java：各類通用工具。

6️⃣ exception
自訂例外類別，例如 DataAccessException.java，用於統一處理 DAO 層錯誤。
7️⃣ config
一般放設定檔（例如 Spring 設定、JDBC、Session、環境變數等）。


[java檔](TripNTreat/src/main/java)

[jar檔](Tripntreat.jar)

| 第一階段                |   第二階段       | 第三階段           | 第四階段          | 第五階段        | 
| ----------------------- | ---------------| ------------------ |------------------|----------------|
| 平台構思                |  分析資料庫表單  | 介面對應資料庫      | 測試資料寫入資料庫 | 確認平台功能    |


- 編號 
- 分點
<img width="680" height="362" alt="WelcomePage" src="https://github.com/user-attachments/assets/483f7012-d5ee-4abb-92ca-070963331d95" />
