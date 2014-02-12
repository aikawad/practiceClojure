;  Clojure Third Day
;
; 7.4 第三天： 一瞥魔鬼
;
; 7.4.1 引用 和 事物內存
;
; ----------
; 引用 "ref"
(ref "Attack of the Clones")

(def movie  (ref "Star Wars") )
movie

(deref movie)
@movie


; ----------
; 修改引用狀態

(alter movie str ": The Empire Strikes Back")

(dosync (alter movie str ": The Empire Strikes Back"))

(dosync (ref-set movie "Star Wars: The Revenge of the Sith"))
@movie ; 原始值被改掉了

; 7.4.2 使用原子
;  原子 "atom"

(atom "Splite at your own risk.")

(def danger (atom "Splite at your own risk."))
danger
@danger
; ----------
; 單一修改 atom
(reset! danger "Split with impunity")
danger
@danger
; ----------
; 大量修改 atom
(def top-sellers (atom []))
(swap! top-sellers conj {:title "Seven Languages in Seven Weeks", :author "Tate"})
(swap! top-sellers conj {:title "Programming Clojure", :author "Halloway"})
; ----------
; 建構原子緩存
(defn create []
  (atom {}))
(defn get [cache key]
  (@cache key))
(defn put
  ([cache value-map]
   (swap! cache merge value-map))
  ([cache key value]
   (swap! cache assoc key value)))
; 使用緩存
(def ac (create))
(put ac :quote "I'm your father, Luke.")
(println (str "Cached item: " (get ac :quote)))
;
; 接下來要講異步的例子
;
; 7.4.3 使用代理
;
(defn twice [x]
  (* 2 x))
(def tribbles (agent 1))

(send tribbles twice)
@tribbles
;
(defn slow-twice [x]
  (do
     (Thread/sleep 5000)
     (* 2 x)))
@tribbles
(send tribbles slow-twice)
; 用 light table 的 repl看不出來，要用console來世試看吧！
@tribbles ; 應該是 4 的

; 7.4.4 future
;
(def finer-things (future (Thread/sleep 5000) "take time"))
@finer-things
; 用途說明： future 允許在計算完成前異步返回的併發編成結構。

; 7.4.5 還差什麼?
;  還需要了解的內容
;  1. 元數據
;  2. Java集成
;  3. 多重方法
;  4. 線程狀態

; 7.4.6 第三天我們學到了什麼
; 書籍內容...

; 7.4.7 第三天自習
;
; 找
;
; q1. 一個隊列實現，隊列為空時阻塞並等待新的元素加入。
;
; 做
; q1. 使用引用在內存中創建一組帳戶的向量，實現用於修改帳戶餘額的借貸函數 debit 和 credit
; q2. 接下來，我會描述一個稱之為理髮師問題(Sleeping barber) 的題目，他是由Edsger Dijkstra於1965年提出的，特點如下
;    attr1. 理髮店接待顧客;
;    attr2. 顧客到達理髮店的時間間隔隨機，範圍10~30毫秒;
;    attr3. 理髮店的等待室里有三把椅子;
;    attr4. 理髮店只有一位理髮師和一把理髮椅;
;    attr5. 當理髮椅為空時，一位顧客坐上去，叫醒理髮師，然後理髮;
;    attr6. 如果所有椅子都被佔用，新來的顧客就會離開;
;    attr7. 理髮需要20毫秒;
;    attr8. 完成理髮後，顧客會起身離開。
;    實現一個多線程程序來決定一個理髮師在10秒內可以為多少位顧客理髮。
;
;
;
;
;
;
