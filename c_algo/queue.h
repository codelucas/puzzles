#ifndef _queue_h
#define _queue_h

// Tail gets popped and head is inserted into

typedef struct QueueNode {
    void *val;
    struct QueueNode *next;
    struct QueueNode *prev;
} QueueNode;

typedef struct Queue {
    // properties
    int size;
    struct QueueNode *head;
    struct QueueNode *tail;

    // methods
    void *(*pop)(struct Queue *queue);
    void (*push)(struct Queue *queue, void *value);
    void (*free)(struct Queue *queue);
} Queue;

void *queue_pop(Queue *queue);
void queue_push(Queue *queue, void *value);
void free_queue(Queue *queue);

Queue *create_queue();

#endif
