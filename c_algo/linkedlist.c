#include <stdio.h>
#include <stdlib.h>
#include <time.h>

/*
 * Misc. linked list code snippets
 */

typedef struct Node {
    struct Node *next;
    int val;
} Node;


// returns a random number between -100 and 100
int r()
{
    return (rand() % 201) - 100;
}

// returns a node with a random value
Node *make_node()
{
    Node *n = malloc(sizeof(Node));
    n->val = r();
    return n;
}

void free_list(Node *head)
{
    while (head) {
        Node *tmp = head;
        head = head->next;
        free(tmp);
    }
}

// builds and returns a linked list with 
// n elements and random element values
Node *build_tree(int n)
{
    if (n <= 0) {
        return NULL;
    }
    Node *head = make_node();
    Node *cur = head;

    int i;
    for (i = 1; i < n; i++) {
        cur->next = make_node(); 
        cur = cur->next;
    }
    return head;
}

// deletes all nodes in the linkedlist that have
// a value which matches the input e
Node *delete_nodes(Node *head, int e)
{
    // Linus Torvalds, fuck yea
    // Ref: http://grisha.org/blog/2013/04/02/linus-on-understanding-pointers/
    Node **pp = &head;
    Node *cur = head;

    while (cur) {
        if (cur->val == e) {
            free(cur);
            *pp = cur->next;
        }
        pp = &cur->next;
        cur = cur->next;
    }
    return head;
}

void print_tree(Node *head)
{
    Node *cur = head;
    int i = 0;
    while (cur) {
        printf("Value of element %d is %d.\n", i++, cur->val);
        cur = cur->next;
    }
}

// prints out the tree in reverse order
// (does not actually reverse the list)
void print_tree_reverse(Node *head, int index)
{
    if (!head) {
        return;
    }
    print_tree_reverse(head->next, index + 1);
    printf("Reverse: Value of element %d is %d.\n",
            index, head->val);
}

// reverses the node ordering of the input linked list
// returns the new head
Node *reverse_list(Node *head, Node *reversed)
{
    if (!head) {
        return reversed;
    }
    // don't lose the head pointer!
    Node *first = head;
    head = head->next;
    first->next = reversed;
    return reverse_list(head, first); 
}

int main(int argc, char *argv[])
{
    srand(time(NULL));

    // test list creation
    Node *head = build_tree(15);
    printf("Just built tree of size 15!\n");
    print_tree(head);
    print_tree_reverse(head, 0);

    // test node deletion from list
    int first_val = head->val;
    int sixth_val = (head + 5)->val;
    printf("First val: %d, sixth val: %d.\n", first_val, sixth_val);
    head = delete_nodes(head, first_val);
    head = delete_nodes(head, sixth_val);
    printf("Nodes of elements: (%d and %d) have been deleted.\n",
            first_val, sixth_val);
    print_tree(head);
 
    // test inplace reversal
    printf("Reversing linked list and printing:\n");
    Node *rev_head = reverse_list(head, NULL);
    print_tree(rev_head);

    // don't leak memory!
    free_list(rev_head);

    return 0;
}
