;anonymous function test1
(print ((lambda (P1 P2) (sqrt (+ (* (- (car P1) (car P2)) (- (car P1) (car P2))) (* (- (car (cdr P1)) (car (cdr P2))) (- (car (cdr P1)) (car (cdr P2))))))) '(4 2) '(4 2)))

;anonymous function test2
(print ((lambda (P1 P2) (sqrt (+ (* (- (car P1) (car P2)) (- (car P1) (car P2))) (* (- (car (cdr P1)) (car (cdr P2))) (- (car (cdr P1)) (car (cdr P2))))))) '(-7 -4) '(17 6.5)))


;non anonymous function 
(defun distance(P1 P2)
    (print(sqrt (+ (* (- (car P1) (car P2)) (- (car P1) (car P2))) (* (- (car (cdr P1)) (car (cdr P2))) (- (car (cdr P1)) (car (cdr P2)))))))
)

;non anonymous function test1
(distance '(4 2) '(4 2))
;non anonymous function test2
(distance '(-7 -4) '(17 6.5))

; The lambda function is more efficient in terms of memory allocation since anonymous functions are not stored in memory unlike defun functions. 
;In the lambda function, we are also referencing the same 4 variables as opposed to referencing the list and returning its head and tail multiple times.