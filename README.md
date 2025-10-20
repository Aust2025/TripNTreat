# TripNTreat
## 旅遊專案
### 行程服務訂購
#### 適合親子共遊


    
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

[doc文件](doc/index.html)


[java檔](TripNTreat/src/main/java)

[jar檔](Tripntreat.jar)

| 第一階段                |   第二階段       | 第三階段           | 第四階段          | 第五階段        | 
| ----------------------- | ---------------| ------------------ |------------------|----------------|
| 平台構思                |  分析資料庫表單  | 介面對應資料庫      | 測試資料寫入資料庫 | 確認平台功能    |


- 編號 
- 分點
<img width="680" height="362" alt="WelcomePage" src="https://github.com/user-attachments/assets/483f7012-d5ee-4abb-92ca-070963331d95" />
