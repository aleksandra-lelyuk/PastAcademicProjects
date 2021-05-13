(defun my_reverse(lst)
    (cond ((null lst) '())
        (t (append (my_reverse (cdr lst)) (list (car lst))))))
 
(defun f-l-swap(lst)
    (let* ((f (car lst))
        (newlst (my_reverse (cdr lst))))
    (append (list (car newlst)) (my_reverse (cdr newlst)) (list f))))


(print (f-l-swap '((a d) f 10 w h)))
(print (f-l-swap '(g 6 p 10 m)))