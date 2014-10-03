#include <stdlib.h>
#include <assert.h>
#include "queue.h"


// adds a new element to the back of the queue
void queue_push(Queue *queue, void *value)
{
    QueueNode *node = malloc(sizeof(QueueNode));
    node->val = value;
    node->next = NULL;
    node->prev = NULL;

    if (queue->tail) {
        queue->head->prev = node;
    } else {
        queue->tail = node;
    }
    queue->head = node;
    queue->size++; 
}

// removes and returns the oldest node in the queue
void *queue_pop(Queue *queue)
{
    QueueNode *popped = queue->tail;
    void *tn = popped->val;

    queue->tail = popped->prev;
    queue->size--;

    free(popped);
    return tn;
}

// incase there are still elements left, clear them all
// and clear the queue itself
void free_queue(Queue *queue)
{
    QueueNode *cur = queue->tail;
    while (cur) {
        // intentionaly don't free values because the
        // tree is being used apart from the queue
        QueueNode *tmp = cur;
        cur = cur->prev;
        free(tmp);
    }

    queue->head = NULL;
    queue->tail = NULL;
    free(queue);
}

Queue *create_queue()
{
    Queue *queue = malloc(sizeof(Queue));
    assert(queue != NULL);

    queue->head = NULL;
    queue->tail = NULL;
    queue->size = 0;

    queue->pop = queue_pop;
    queue->push = queue_push;
    queue->free = free_queue;

    return queue;
}
