#include <stdio.h>
#include <stdlib.h>
#include "queue.h"

/*
 * Tree related algorithms:
 * (construction, traversal, etc)
 */

typedef struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
} TreeNode;

// this can't go inside queue.c/h because it requires
// TreeNode specific code
void print_queue(Queue *queue)
{
    if (!queue->tail) {
        printf("Queue is empty!\n");
        return;
    }

    int count = 0;
    QueueNode *cur = queue->tail;
    while (cur) {
        TreeNode *tn = cur->val;
        printf("Queue elem %d: %d\n", count++, tn->val);
        cur = cur->prev;
    }
    cur = NULL;
}

// creates a new treenode with the value set to the input
TreeNode *make_tree_node(int val)
{
    TreeNode *n = malloc(sizeof(TreeNode));
    n->val = val;
    return n;
}

// inserts a node into a given root following binary
// search tree rules, allow duplicate nodes and assumes
// the original tree root is never null
void tree_insert(TreeNode *root, TreeNode *node)
{
    if (node->val <= root->val) {
        if (!root->left) {
            root->left = node;
        } else {
            tree_insert(root->left, node);
        }
    } else {
        if (!root->right) {
            root->right = node;
        } else {
            tree_insert(root->right, node);
        }
    }
}

// builds and returns the root of a binary tree with
// random values
TreeNode *build_tree()
{
    int tree_elements[] = {5, 4, 6, 3, 7, 5};
    TreeNode *root = make_tree_node(tree_elements[0]);

    int i;
    int n = sizeof(tree_elements) / sizeof(int);
    TreeNode *node;
    for (i = 1; i < n; i++) {
        node = make_tree_node(tree_elements[i]);
        tree_insert(root, node);
    }
    node = NULL;
    return root; 
}

void free_tree(TreeNode *root)
{
    if (root) {
        free_tree(root->left);
        free_tree(root->right);
        free(root);
    }
}

// in-order depth first search to print out all node values
void dfs(TreeNode *root)
{
    if (!root) {
        return;
    }
    dfs(root->left);
    printf("%d ", root->val);
    dfs(root->right);
}

// in-order breadth first search to print out all node values
void bfs(TreeNode *root)
{
    if (!root) {
        return;
    }
    Queue *queue = create_queue();         
    queue->push(queue, root);

    while (queue->size > 0) {
        TreeNode *cur = queue->pop(queue);
        printf("%d ", cur->val);
        if (cur->left) {
            queue->push(queue, cur->left);
        }
        if (cur->right) {
            queue->push(queue, cur->right);
        }
    }
    queue->free(queue);
}

// print out all paths of node values in a tree via DFS
void all_paths(TreeNode *root, int *path, int n)
{
    if (!root) {
        return;
    } else {
        path[n] = root->val;
    }

    // if root is a leaf node
    if (!root->left && !root->right) {
        int i;
        // <= because n is an index
        for (i = 0; i <= n; i++) {
            printf("%d ", path[i]);
        }
        printf("\n");
    } else {
        all_paths(root->left, path, n + 1);
        all_paths(root->right, path, n + 1);
    }
}

// print out the node values of a tree in level order using BFS
void level_order(TreeNode *root)
{
    if (!root) {
        return;
    }

    Queue *queue = create_queue();
    queue->push(queue, root);

    int next_counter = 0;
    int cur_counter = 1;

    while (queue->size > 0) {
        TreeNode *cur = queue->pop(queue);
        printf("%d ", cur->val);
        cur_counter--;

        if (cur->left) {
            queue->push(queue, cur->left);
            next_counter++;
        }
        if (cur->right) {
            queue->push(queue, cur->right);
            next_counter++;
        }
        if (cur_counter == 0) {
            // just a newline to seperate levels
            printf("\n");
            // reset counters
            cur_counter = next_counter;
            next_counter = 0;
        }
    }
    queue->free(queue);
}

int main(int argc, char *argv[])
{
    TreeNode *tree = build_tree();
    printf("Beginning depth first traversal:\n");
    dfs(tree);
    printf("\nCorrect order:\n3 4 5 5 6 7\n");

    printf("\nBeginning breadth first traversal:\n");
    bfs(tree);
    printf("\nCorrect order:\n5 4 6 3 5 7\n");

    printf("\nPrinting out all paths of the tree:\n");
    int path[6];
    all_paths(tree, path, 0);
    printf("Correct answer:\n5 4 3\n5 4 5\n5 6 7\n");

    printf("\nPrinting out the tree in level order:\n");
    level_order(tree);
    printf("Correct answer:\n5\n4 6\n3 5 7\n");

    free_tree(tree);

    return 0;
}
