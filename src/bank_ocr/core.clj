(ns bank-ocr.core
  "Functions to solve the Bank OCR kata."
  (:gen-class))


;; Entry: A sequence of three lines which represent an account number.
;; Account Number: A sequence of 9 digits identifying an account.


(def digit-map
  "Mapping of the 9-character sequences that makes up each digit."
  {[\space \_ \space \| \space \| \| \_ \|] 0
   [\space \space \space \space \space \| \space \space \|] 1
   [\space \_ \space \space \_ \| \| \_ \space] 2
   [\space \_ \space \space \_ \| \space \_ \|] 3
   [\space \space \space \| \_ \| \space \space \|] 4
   [\space \_ \space \| \_ \space \space \_ \|] 5
   [\space \_ \space \| \_ \space \| \_ \|] 6
   [\space \_ \space \space \space \| \space \space \|] 7
   [\space \_ \space \| \_ \| \| \_ \|] 8
   [\space \_ \space \| \_ \| \space \_ \|] 9})


(defn- pad
  "Ensure that a line has 27 characters by padding with spaces.
  This normalizes the top line, which can empty above '1' and '4'."
  [line]
  (take 27 (concat line (repeat \space))))


(defn parse
  "Converts an entry into an account number."
  [entry]
  (->> entry
       (map pad)
       (map #(partition-all 3 %))
       (apply map vector)
       (map flatten)
       (map #(get digit-map %))
       ;; TODO Remove the String representation from this function.
       (apply str)))


;; Command Line

(defn- exit
  "Exit with error after reporting a message."
  [message]
  (println message)
  (System/exit 1))

(defn -main [& args]
  (if (seq args)
    (let [file (first args)]
      (with-open [reader (clojure.java.io/reader file)]
        (let [entries (partition-all 3 4 (line-seq reader))]
          (doseq [entry entries]
            (println (parse entry))))))
    (exit "No file to parse.")))
