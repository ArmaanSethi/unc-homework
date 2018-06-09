#include <string.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

struct Node{
	char name[20];
	struct Node *father;
	struct Node *mother;
};

struct Node* initNode(char name[20]){
	struct Node *node = (struct Node*)malloc(sizeof(struct Node));
	strcpy(node -> name, name);
	node -> father = NULL;
	node -> mother = NULL;
	return node;
}

struct Node* getNode(char name[20], struct Node *cur){
	if (cur == NULL){
		return NULL;
	}
	if (strcmp(name, cur->name) == 0){
		return cur;
	}
	else{
		struct Node *mother = getNode(name, cur->mother);
		struct Node *father = getNode(name, cur->father);
		if (mother != NULL){
			return mother;
		}
		if (father != NULL){
			return father;
		}
		return NULL;
	}
}

struct Node* getDescendent(char name[20], struct Node *cur){
	if (cur == NULL)
		return NULL;
	if (cur -> father != NULL){
		if (strcmp(name, cur -> father -> name) == 0){
			return cur;
		}
	}
	if (cur->mother != NULL)
	{
		if (strcmp(name, cur->mother->name) == 0)
		{
			return cur;
		}
	}
	struct Node *father = getDescendent(name, cur -> father);
	struct Node *mother = getDescendent(name, cur -> mother);
	if (father != NULL)
		return father;
	else{
		return mother;
	}
	return NULL;
}

/*bool containsNode(char name[20], struct Node *cur){
	if (cur == NULL)
	{
		return false;
	}
	if (strcmp(name, cur->name) == 0)
	{
		return true;
	}
	else
	{
		return (containsNode(name, cur->mother) || containsNode(name, cur->father));
	}
}*/

bool add(struct Node *cur){
	puts("Please specify a relation to add");
	char buffer[100];
	scanf("%s", buffer);
	char args[3][20]; //0 = relationship, 1 = ancestor, 2 = descendent
	int i = 0;

	//parse arguments
	for (char *p = strtok(buffer, "(,)"); p != NULL; p = strtok(NULL, " (,)"))
	{
		strcpy(args[i],p);
		i++;
	}

	//check if valid relationship
	if (strcmp(args[0], "mother") != 0 && strcmp(args[0], "father") != 0){
		puts("invalid relationship");
		return false;
	}
	//check if descendent name exists
	/*if (!containsNode(args[2], cur)){
		printf("%s name not found\n", args[2]);
		return false;
	}*/
	
	struct Node *descendent = getNode(args[2], cur);

	if (descendent == NULL){
		printf("%s name not found\n", args[2]);
		return false;
	}

	//mother relationship already exists
	if (strcmp(args[0], "mother") == 0 && descendent->mother != NULL){
		puts("relationship aready exists");
		return false;
	}
	//father relationship already exists
	if (strcmp(args[0], "father") == 0 && descendent->father != NULL)
	{
		puts("relationship aready exists");
		return false;
	}

		//init node with ancestor's name and set descendents pointers
	if ((int)strcmp(args[0], "mother") == 0){
		descendent->mother = initNode(args[1]);
	}
	else if ((int)strcmp(args[0], "father") == 0){
		descendent->father = initNode(args[1]);
	}
	return true;
}

bool delete (char name[20], struct Node *cur, struct Node **root){
	if (cur == NULL){
		return false;
	}
	struct Node *node = getNode(name, cur);
	if (node == NULL){
		puts("name not found");
		return false;
	}
	if (node -> father != NULL){
		delete (node->father->name, cur, root);
	}
	if (node -> mother != NULL){
		delete (node->mother->name, cur, root);
	}
	struct Node *descendent = getDescendent(name, cur);
	if (descendent == NULL){
		free(node);
		node = NULL;
		*root = NULL;
		return true;
	}
	if (descendent -> father != NULL){
		if (strcmp(name, descendent->father->name) == 0)
		{
			descendent->father = NULL;
		}
	}
	if (descendent -> mother != NULL){
		if (strcmp(name, descendent->mother->name) == 0){
			descendent->mother = NULL;
		}
	}
	free(node);
	node = NULL;
	return true;
}

void print(struct Node *cur, int depth){
	if (cur == NULL)
		return;
	for (int i = 0; i < depth; i++)
	{
		printf("	");
	}
	puts(cur->name);
	if (cur -> father != NULL)
		print(cur -> father, depth + 1);

	if (cur -> mother != NULL)
		print(cur -> mother, depth + 1);
}

int main(){
	char buffer[20];
	printf("Enter your name\n");
	scanf("%s", buffer);

	struct Node *root = initNode(buffer);

	while(strcmp(buffer, "quit") != 0){
		printf("Please specify whether to add or delete an entry, or print the tree.\n");
		scanf("%s", buffer);
		if (strcmp(buffer, "add") == 0){
			add(root);
		}
		else if (strcmp(buffer, "delete") == 0){
			puts("Please specify the name to delete.");
			scanf("%s", buffer);
			delete(buffer, root, &root);
		}
		else if (strcmp(buffer, "print") == 0){
			print(root, 0);
		}
		else if (strcmp(buffer, "quit") != 0){
			puts("invalid command");
		}
	}

	return 0;
}

