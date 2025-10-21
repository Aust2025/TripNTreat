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
ProjectRoot/
├── src/main/java/              
│
│   ├── config/                    
│   │   
│   │
│   ├── controller/                 
│   │   ├── employee
│   │   │    ├──AddEmployee.java
│   │   │    ├──Backstage.java
│   │   │    ├──ConfirmOrder.java
│   │   │    ├──EmployeeLogin.java
│   │   │    ├──OrderReport.java
│   │   │    ├──UpdateEmployee.java
│   │   ├── member
│   │   │    ├──AddMember.java
│   │   │    ├──AddMemberError.java
│   │   │    ├──AddMemberSuccess.java
│   │   │    ├──Login.java
│   │   │    ├──LoginError.java
│   │   │    ├──LoginSuccess.java  
│   │   │    ├──UpdateMember.java  
│   │   ├── service
│   │   │    ├──AddService.java
│   │   │    ├──AddTrip.java
│   │   │    ├──UpdateService.java
│   │   ├── trorder
│   │   │    ├──AddTrorder.java
│   │   │    ├──CheckTrorder.java
│   │   │    ├──DeleteTrorder.java
│   │   │    ├──OutputTrorder.java
│   │   ├── WelcomePage.java   
│   │
│   ├── exception/                  
│   │   ├── DataAccessException.java    
│   │   
│   │
│   ├── po/                        
│   │   ├── Employee.java             
│   │   ├── Member.java               
│   │   ├── Service.java          
│   │   ├── Trip.java              
│   │   ├── Trorder.java          
│   │   └── TrorderDetail.java          
│   │
│   │   ├── po.dao/                 
│   │   │   ├── EmployeeDao.java
│   │   │   ├── MemberDao.java
│   │   │   ├── ServiceDao.java  
│   │   │   ├── TripDao.java
│   │   │   ├── TrorderDao.java
│   │   │   ├── TrorderDetailDao.java  
│   │   │   └── impl/               
│   │   │       ├── EmployeeDaoImpl.java
│   │   │       ├── MemberDaoImpl.java
│   │   │       ├── ServiceDaoImpl.java  
│   │   │       ├── TripDaoImpl.java
│   │   │       ├── TrorderDaoImpl.java
│   │   │       └── TrorderDetailDaoImpl.java
│   │   └── po.service/             
│   │       ├── EmployeeService.java
│   │       ├── MemberService.java
│   │       ├── ServiceService.java  
│   │       ├── TripService.java
│   │       ├── TrorderService.java
│   │       ├── TrorderDetailService.java  
│   │       └── impl/              
│   │           ├── EmployeeServiceImpl.java
│   │           ├── MemberServiceImpl.java
│   │           ├── ServiceServiceImpl.java  
│   │           ├── TripServiceImpl.java
│   │           ├── TrorderServiceImpl.java
│   │           └── TrorderDetailServiceImpl.java
│   │
│   │
│   └── util/
│       ├── ChartUtil.java
│       ├── CreateExcel.java  
│       ├── DbConnection.java           
│       ├── InputUtil.java                     
│       ├── Tool.java           
│       └── ToolChange.java                   
│
└── src/main/resources/         


## 操作說明
#### 安裝 JDK 11 並設定環境變數
#### 使用 MySQL 建立資料庫，執行 sql/setup.sql
#### 執行jar檔案
#### 設定好資料庫連線Dbconnection
#### 登入後可使用會員或員工後台管理功能



| 第一階段                |   第二階段       | 第三階段           | 第四階段          | 第五階段        | 
| ----------------------- | ---------------| ------------------ |------------------|----------------|
| 平台構思                |  分析資料庫表單  | 介面對應資料庫      | 測試資料寫入資料庫 | 確認平台功能    |



