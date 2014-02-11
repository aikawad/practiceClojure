;  Clojure Second Day

; 7.3.1 用loop和recur遞歸

(defn size [v]
 (if (empty? v)
   0
   (inc (size (rest v)))))

 (size [1 2 3])
 (size [1 3])
 (size [])

 ;  (loop [x x-initial-value, y y-initial-value] (do-something-with x y))
   (loop [x 4, y 5] (+ x y))
   (loop [x 1] x)


 ; 尾遞迴寫法
 ; c 變量是 累加器
   (defn size [v]
     (loop [l v, c 0]
       (if (empty? l)
         c
         (recur (rest l) (inc c)))))

(size [6 5 4])

; 不過一般用途上，不太需要用到recur操作，Clojure也提供了一些優秀的遞迴替代技術，如後續的延遲序列。


; 7.3.2 序列

; 說明： 序列包含了所有Clojure的各種容器。也就是封裝了所有的Clojure集合、字符串、文件結構(串流、目錄)、。

[1 2 3]
(rest [1 2 3])


(every? number? [1 2 3 :four])
(some nil? [1 2 nil])

(not-every? odd? [1 3 5])
(not-every? odd? [2 3 5])

(not-any? number? [:one :two :three])

; 修改序列
(def words ["luke" "chewie" "han" "lando"])
(filter (fn [word] (> (count word) 4)) words)

(map (fn [x] (* x x)) [1 1 2 3 5])

(def colors ["red" "blue"])
(def toys ["block" "car"])

(for [x colors] (str "I like " x))

(for [x colors, y toys] (str "I like " x " " y "s"))

(defn small-word? [w] (< (count w) 4))

(for [x colors, y toys, :when (small-word? y)] (str "I like " x " " y "s"))

; reduce

(reduce + [1 2 3 4])

(reduce * [1 2 3 4 5])

(sort [3 1 2 4])

; sort by defn
(defn abs [x] (if (< x 0) (- x) x))
(sort-by abs [-1 -4 3 2])

; 7.3.3 延遲計算
