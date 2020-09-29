NUME : UNGUREANU ANDREI
GRUPA : 324 CC
GRAD DIFICTULATE: PUTIN DIFICIL

!!! Am incarcat tema din nou pentru ca nu imi mergea clasa Test din arhiva incarcata , dar pe calculator imi mergea.Am anuntat despre aceasta schimbare pe profesoara de curs.

	Am implementat partial Cerinta1

	Pentru cerinta 1 , am implementat toate clasele cerute.

	In clasa Store, am intializat magazinul si am folosit metode pentru a intoarce 
colectiile de item-uri si departamente in obiecte de tip Vector.De asemenea, am implementat
si metode de gasire a unui item sau departament dupa id si nume. In store mai am implementata
si metoda getInstance care o folosesc pentru a stabilit un singleton.

	In clasa Departament am initializat vectorul pentru clienti care doresc sa cumpere ceva
si clienti care deja au cumparat.De asemenea , obtin vectorii pentru listarea item-urilor si
departamentelor, precum si observerii si notificarea lor.

	Clasa Item implementeaza un element de tip Item.Aceasta ii atribuie un nume, id si pret.

	Clasa ItemList implementeaza o lista dublu inlantuita si are 2 clase interne.
Asadar clasa interna Node implementeaza un nod al listei si operatii posibile asupra listei,
cum ar fi obtinerea urmatorului sau precedentului nod sau atribuirea elementului pentru fiecare
nod.Clasa ItemIterator implementeaza un listIterator care il voi folosi destul de des pentru
a parcurge o colectie.
 Asadar, clasa ItemList implementeaza functionalitatile unei liste dublu inlantuite.Metoda 
principala este add(Item element), care adauga un nod in functie de comparator initiat in 
clasa ComparePrice din fisierul Magazin.Asadar, se insereaza crescator in lista.Alte metode 
importante stabilite in ItemList sunt getNode(index) care intoarce nodul de la pozitia index,
indexOf(Item item) care intoarce index-ul unui item, atunci cand avem de intors un int.
In clasa ItemIterator se implementeaza operatiile pentru iterarea listei dublu inlantuite.
Asadar, sunt implementate metode ca hasNext(), hasPrevious(), next() si altele.

	In clasele MusicDepartment , VideoDepartment, SoftwareDepartment si BookDepartment , 
implementez ca visit metodele accept , apeland pentru fiecare tip metoda visit.

	In clasa Notification fac Enumerarea NotificationType care contine ADD,REMOVE SAU 
MODIFY.Aceasta clasa o folosesc atunci cand trimit notificare catre toti observerii ca s-a 
modificat pretul sau s-a adaugat/sters un element in wishlist sau shoppingcart.

	Functionalitatea de baza a clasei ShoppingCart este de a crea un shoppingCart 
pentru un customer, care are asociat un buget.Asadar, in shoppingCart am metoda de a calcula
pretul total al item-urilor, de a adauga un element in cos, si toate aceste metode tin cont
de buget.O alta trasatura de baza a clasei ShoppingCart este functionalitatea metodelor visit
pentru toate cele 4 obiecte : BookDepartment, MusicDepartment, SoftwareDepartment, VideoDepartment.
Astfel, pentru fiecare din ele, implementez conditia necesara.

	In clasa WishList am de asemenea, metode de adaugare , stergere si obtinerea pretului
total al tuturor produselor, care apeleaza metode din ItemList .

In clasa Test, am testat functionalitatea celor implementate in celelalte clase.Asadar, 
creat obiecte de tip File pentru fiecare fisier si parcurg fisierele cu ajutorul clasei
Scanner.De asemenea , pentru a  delimita cuvintele cu delimitatorul ";" , am creat un 
vector String cu split , care imparte linia in mai multe elemente dupa delimitator.
Asadar, pentru fiecare fisier am facut citirea si am setat campurile in clase dupa input-ul 
primit din fisiere.

De asemenea , verific de fiecare data ce departament am prin verificarea daca prima litera e
S ceea inseamna ca e Software Department sau M daca e musicDepartment.

Pentru tratarea evenimentelor, folosesc un switch in care tratez evenimentele.
Pentru addItem , verific in care departament trebuie sa adaug , obtin clientul si adaug astfel 
noul element in ShoppingCart sau WishList , verificand daca locul unde trebuie de adaugat 
incepe cu S sau W.De asemenea, listez fiecare item si scriu in fisierul output.txt.
La delItem , procedez ca la addItem doar ca in loc de adaugare, fac stergere.

Pentru addProduct , de asemenea verific unde adaug si ce adaug , avand item-ul setat.Asadar,
mai folosesc si notificarea observer-ilor prin notifyAllObservers.
ModifyProduct e similar, doar ca folosesc notificare pentru a spune ca s-a modificat pretul 
si setez noul pret.
Pentru delProduct , obtin departamentul , item-ul si fac un ListIterator pentru a itera
peste toate produsele si a obtine anume pe acel care trebuie sters dupa ID.

Event-ul getTotal foloseste getTotalPrice pentru a calcula suma tuturor produselor din WishList
sau ShoppingCART.

***Restul nu le-am implementat.

Nu am reusit sa implementez prima cerinta total, si am implementat doar partial.Am afisat in 
output.txt item-urile , clientii pentru a demonstra ca s-a citit bine.