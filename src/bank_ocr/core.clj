(ns bank-ocr.core
  "Functions to solve the Bank OCR kata."
  (:gen-class))


;; Entry: A sequence of three lines which represent an account number.
;; Account Number: A sequence of 9 digits identifying an account.


;; Parse OCR Entries

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
       (map #(get digit-map % \?))))


;; Checksums (Verify Account Numbers)

(def positions
  "Position sequence constant for calculating checksums."
  (range 9 0 -1))


(defn- checksum
  "Calculate checksum for account.
  The calculation is (d1+2*d2+3*d3 +..+9*d9) mod 11 with the position number
  relative to the end of the sequence: (d9 d8 d7 ... d1)"
  [acct]
  (mod (apply + (map * acct positions))
       11))


(defn status
  "Returns true if an account number is valid, 'ERR' is the checksum does not
  validate, and 'ILL' if the number contains illegible digits."
  [acct]
  (if (every? nat-int? acct)
    (when (pos? (checksum acct)) "ERR")
    "ILL"))


;; Command Line

(defn- exit
  "Exit with error after reporting a message."
  [message]
  (println message)
  (System/exit 1))


(defn- report-entry
  "Parse and print entry results to *out*. For valid accounts, will just print
  the account number. If there is a problem, print out an error code after the
  account number."
  [entry]
  (let [acct (parse entry)]
    (print (clojure.string/join acct))
    (if-let [st (status acct)]
      (print (str \space st)))
    (println)))


(defn -main [& args]
  (if (seq args)
    (let [file (first args)]
      (with-open [reader (clojure.java.io/reader file)]
        (let [entries (partition-all 3 4 (line-seq reader))]
          (doseq [entry entries]
            (report-entry entry)))))
    (exit "No file to parse.")))
